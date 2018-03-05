package com.ny.config;

import com.ny.strategy.UrlHttpSessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
public class SessionConfiguration {

    //@Bean
    public HttpSessionStrategy urlHttpSessionStrategy() {
        return new UrlHttpSessionStrategy();
    }
}
