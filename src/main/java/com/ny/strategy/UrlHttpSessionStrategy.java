package com.ny.strategy;

import org.springframework.session.Session;
import org.springframework.session.web.http.HttpSessionManager;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class UrlHttpSessionStrategy implements HttpSessionManager, HttpSessionStrategy {

    private static final String DEFAULT_ALIAS = "0";

    private static final String DEFAULT_URL_ALIAS = "sessionId";

    @Override
    public String getCurrentSessionAlias(HttpServletRequest request) {
        return DEFAULT_ALIAS;
    }

    @Override
    public Map<String, String> getSessionIds(HttpServletRequest request) {
        Map<String, String> result = new LinkedHashMap<>();
        result.put(DEFAULT_ALIAS, request.getParameter(DEFAULT_URL_ALIAS));
        return result;
    }

    @Override
    public String encodeURL(String url, String sessionAlias) {
        return url;
    }

    @Override
    public String getNewSessionAlias(HttpServletRequest request) {
        return DEFAULT_ALIAS;
    }

    @Override
    public String getRequestedSessionId(HttpServletRequest request) {
        return request.getParameter(DEFAULT_URL_ALIAS);
    }

    @Override
    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
        //nothing to do
    }

    @Override
    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
        //nothing to do
    }
}
