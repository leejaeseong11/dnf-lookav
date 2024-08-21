package com.dnf.lookav.avatar.repository;

import com.dnf.lookav.avatar.domain.Avatar;
import com.dnf.lookav.avatar.domain.AvatarItem;
import com.dnf.lookav.avatar.domain.constatns.AvatarSlot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarItemRepository extends JpaRepository<AvatarItem, Long> {
    AvatarItem findByAvatarAndSlot(Avatar avatar, AvatarSlot slot);
}
