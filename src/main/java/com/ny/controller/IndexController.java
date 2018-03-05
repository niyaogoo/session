package com.ny.controller;

import com.ny.config.TomcatMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class IndexController {

    AtomicInteger total = new AtomicInteger();

    @PostMapping("/foo/add")
    public String addFoo() throws Throwable {
        Thread.sleep(1000);
        System.out.println(total.incrementAndGet());
        return UUID.randomUUID().toString();
    }

    @GetMapping("/cookies")
    public String hello(HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                printField(cookie);
            }
        }
        String userIdAlias = "userId";
        String userId = session.getAttribute(userIdAlias) == null ?
                null : session.getAttribute(userIdAlias).toString();
        if(userId == null) {
            System.out.println("user not login");
            session.setAttribute(userIdAlias, "user");
        } else {
            System.out.println("user already login");
        }
        return "hello";
    }

    private void printField(Cookie cookie) {
        System.out.print("name: " + cookie.getName());
        System.out.print("value: " + cookie.getValue());
        System.out.println();
    }


}
