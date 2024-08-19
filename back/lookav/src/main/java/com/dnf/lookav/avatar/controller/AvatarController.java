package com.dnf.lookav.avatar.controller;

import com.dnf.lookav.common.AwsS3;
import com.dnf.lookav.common.DnfApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/avatar")
@RequiredArgsConstructor
public class AvatarController {
    private final DnfApi dnfApi;
    private final AwsS3 awsS3;

    @GetMapping
    public String search() {
        JSONObject result = dnfApi.getCharacterId("cain", "안중");
        String resultString = result.getJSONArray("rows").getJSONObject(0).getString("characterId");
        awsS3.uploadImage(
                resultString,
                "https://img-api.neople.co.kr/df/servers/cain/characters/e3a8dd3a61f2b5716bd54aeaba75b2e3?zoom=1");
        return resultString;
    }

    @PostMapping("/add")
    public String add(String characterName, String server) {
        return "test";
    }
}
