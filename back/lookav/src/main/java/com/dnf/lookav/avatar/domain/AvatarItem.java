package com.dnf.lookav.avatar.domain;

import com.dnf.lookav.avatar.domain.constatns.AvatarSlot;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AvatarItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avatar_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    private AvatarSlot slot;
}
