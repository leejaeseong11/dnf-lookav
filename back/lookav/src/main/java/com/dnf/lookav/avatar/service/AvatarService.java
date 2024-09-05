package com.dnf.lookav.avatar.service;

import com.dnf.lookav.avatar.dto.AvatarDto;
import com.dnf.lookav.avatar.dto.AvatarItemDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AvatarService {
    // 캐릭터 아이디와 서버로 아바타 추가
    Long saveAvatar(AvatarDto avatarDto);

    // 아바타 페이징 처리, 필터링(직업별, 슬롯 아이템 이름별), 정렬(인기순, 최신순)
    Page<AvatarDto> findAvatarList(Pageable pageable);

    // 캐릭터가 착용한 아바타 리스트 반환
    List<AvatarItemDto> findAvatarItemList(String characterId);
}
