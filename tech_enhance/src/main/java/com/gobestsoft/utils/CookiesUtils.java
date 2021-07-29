package com.gobestsoft.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;

public class CookiesUtils {

    public static final String Token_Name = "token";

    public static void sentToken(String value, String tokenName, HttpServletResponse response) throws Exception {
        StringBuffer token = new StringBuffer( 32 );
        byte[] bytes = MessageDigest.getInstance( "md5" ).digest( value.getBytes( "utf-8" ) );

        for (int i = 0; i < bytes.length; i++) {
            token.append( Integer.toHexString( (bytes[i] & 0xFF) | 0x100 ).toUpperCase().substring( 1, 3 ) );
        }
        response.addCookie( new Cookie( tokenName, token.toString() ) );
    }

    public static boolean isContainToken(String tokenName, HttpServletRequest request) {

        Cookie[] cs = request.getCookies();
        for (Cookie cookie : cs) {
            String token = cookie.getName();
            if (!StringUtils.isEmpty( token ) && tokenName.equals( token )) {
                return true;
            }
        }
        return false;
    }

}
