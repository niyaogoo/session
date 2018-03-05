package com.ny;

import com.ny.config.TomcatMetrics;
import feign.RequestLine;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.AbstractProtocol;
import org.apache.coyote.ProtocolHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.servlet.Servlet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@EnableRedisHttpSession
@SpringBootApplication
@Configuration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({ Servlet.class, Tomcat.class })
    @ConditionalOnWebApplication
    public TomcatMetrics advancedTomcatMetrics(){
        return new TomcatMetrics();
    }

    @Bean
    public EmbeddedServletContainerFactory tomcatFactory() {
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        tomcatFactory.setPort(8001);
        tomcatFactory.addConnectorCustomizers(new TomcatConnector());
        return tomcatFactory;
    }

    class TomcatConnector implements TomcatConnectorCustomizer {

        @Override
        public void customize(Connector connector) {
            ProtocolHandler handler = connector.getProtocolHandler();
            if(handler instanceof AbstractProtocol) {
                AbstractProtocol protocol = (AbstractProtocol)handler;
                protocol.setMaxConnections(5);
                protocol.setMaxThreads(5);
                protocol.setAcceptCount(5);
                protocol.setAcceptorThreadCount(5);
                protocol.setConnectionTimeout(5000);
            }
        }
    }

}