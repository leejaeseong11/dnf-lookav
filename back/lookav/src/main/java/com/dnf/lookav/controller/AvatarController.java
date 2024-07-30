package com.dnf.lookav.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AvatarController {
    public static String encodeURIComponent(String component) {
        String result = null;

        try {
            result = URLEncoder.encode(component, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            result = component;
        }
        return result;
    }

    @GetMapping("/")
    public String get() {
        log.info("test={}", encodeURIComponent("안중"));
        return "test";
    }
}
