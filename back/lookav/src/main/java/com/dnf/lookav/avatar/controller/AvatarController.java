package com.dnf.lookav.avatar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AvatarController {
    @GetMapping("/")
    public String get() {
        return "test";
    }
}
