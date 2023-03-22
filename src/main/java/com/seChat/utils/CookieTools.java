package com.seChat.utils;

import jakarta.servlet.http.Cookie;

public class CookieTools {
    public static Cookie getSecureCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        return cookie;
    }
}
