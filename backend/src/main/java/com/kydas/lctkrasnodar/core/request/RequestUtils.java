package com.kydas.lctkrasnodar.core.request;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

public class RequestUtils {
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
