package com.dnf.lookav.avatar.service;

import com.dnf.lookav.avatar.dto.AvatarDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AvatarService {
    // 캐릭터 아이디와 서버로 아바타 추가
    Long saveAvatar(AvatarDto avatarDto);

    // 아바타 페이징 처리, 필터링(직업별, 슬롯 아이템 이름별), 정렬(인기순, 최신순)
    Page<AvatarDto> findAvatarList(Pageable pageable);
}
