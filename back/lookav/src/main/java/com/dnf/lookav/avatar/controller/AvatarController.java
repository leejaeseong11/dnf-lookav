package com.dnf.lookav.avatar.controller;

import com.dnf.lookav.common.DnfApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AvatarController {
    private final DnfApi dnfApi;

    @GetMapping("/avatar")
    public String search() {
        JSONObject result = dnfApi.getCharacterId("cain", "안중");
        return result.getJSONArray("rows").getJSONObject(0).getString("characterId");
    }

    @PostMapping("/avatar/add")
    public String add(String characterName, String server) {
        return "test";
    }
}
