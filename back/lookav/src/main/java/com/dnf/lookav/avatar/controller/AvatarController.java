package com.dnf.lookav.avatar.controller;

import com.dnf.lookav.avatar.dto.AvatarDto;
import com.dnf.lookav.avatar.exception.ErrorCode;
import com.dnf.lookav.avatar.exception.MyException;
import com.dnf.lookav.avatar.service.AvatarService;
import com.dnf.lookav.common.AwsS3;
import com.dnf.lookav.common.DnfApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class AvatarController {
    private final DnfApi dnfApi;
    private final AwsS3 awsS3;
    private final AvatarService avatarService;

    @PostMapping("/add")
    public String add(@RequestBody AvatarDto avatarDto) {
        avatarService.saveAvatar(avatarDto);
        return "test";
    }

    @GetMapping
    public String search(
            @RequestParam(value = "date", defaultValue = "all") String date) { // todo + frontend
        JSONObject result = dnfApi.getCharacterId("cain", "안중");
        String resultString = result.getJSONArray("rows").getJSONObject(0).getString("characterId");
        awsS3.uploadImage(
                resultString,
                "https://img-api.neople.co.kr/df/servers/cain/characters/e3a8dd3a61f2b5716bd54aeaba75b2e3?zoom=1");
        return resultString;
    }

    @GetMapping("/add")
    public String adds(String characterName, String server) {
        throw new MyException(ErrorCode.AWS_SERVER_ERROR);
    }
}
