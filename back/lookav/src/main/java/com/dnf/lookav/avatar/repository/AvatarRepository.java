package com.dnf.lookav.avatar.repository;

import com.dnf.lookav.avatar.domain.Avatar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findByCharacterId(String characterId);
}
