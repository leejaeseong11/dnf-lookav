package com.dnf.lookav.common;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Util {
    public static String encodeURIComponent(String component) {
        String result = null;

        try {
            result = URLEncoder.encode(component, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            result = component;
        }
        return result;
    }

    public static String getCharacterId(String characterName, String serverId) {

        return "";
    }
}
