package com.shiropure.campuslink.utils;

import javax.servlet.http.HttpServletRequest;

public class IPTool {
    public static String getIP(HttpServletRequest request) {
        String clientIP;

        // Check for the X-Forwarded-For header, which might be set by proxies.
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null) {
            clientIP = forwardedFor.split(",")[0]; // This header can contain multiple IPs, the first one is the original client IP.
        } else {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
    }
}
