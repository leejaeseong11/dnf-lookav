package com.dnf.lookav.avatar.service;

import com.dnf.lookav.avatar.domain.Server;
import com.dnf.lookav.avatar.dto.AvatarDto;

import com.dnf.lookav.avatar.repository.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final AvatarRepository avatarRepository;

    @Override
    public Long saveAvatar(String characterName, Server server) {
        return null;
    }

    @Override
    public Page<AvatarDto> findAvatarList(Pageable pageable) {
        return null;
    }
}
