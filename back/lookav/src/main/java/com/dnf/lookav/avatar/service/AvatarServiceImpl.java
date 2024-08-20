package com.dnf.lookav.avatar.service;

import com.dnf.lookav.avatar.domain.Avatar;
import com.dnf.lookav.avatar.dto.AvatarDto;
import com.dnf.lookav.avatar.repository.AvatarItemRepository;
import com.dnf.lookav.avatar.repository.AvatarRepository;
import com.dnf.lookav.common.AwsS3;
import com.dnf.lookav.common.DnfApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AvatarServiceImpl implements AvatarService {
    private final DnfApi dnfApi;
    private final AwsS3 awsS3;
    private final AvatarRepository avatarRepository;
    private final AvatarItemRepository avatarItemRepository;

    @Override
    public Long saveAvatar(AvatarDto avatarDto) {
        JSONObject characterInfo =
                dnfApi.getCharacterAvatar(avatarDto.getServerId(), avatarDto.getCharacterId());
        avatarDto.setJob(characterInfo.getString("jobName"));
        avatarDto.setLikes(0);
        avatarDto.setRegisterDate(LocalDateTime.now());
        awsS3.uploadImage(avatarDto.getCharacterId(), avatarDto.getServerId());
        avatarDto.setImage(AwsS3.IMAGE_LINK + avatarDto.getCharacterId() + ".jpg");
        avatarRepository.save(Avatar.toEntity(avatarDto));

        // todo: sava avatar
        JSONArray avatars = characterInfo.getJSONArray("avatar");

        return null;
    }

    @Override
    public Page<AvatarDto> findAvatarList(Pageable pageable) {
        return null;
    }
}
