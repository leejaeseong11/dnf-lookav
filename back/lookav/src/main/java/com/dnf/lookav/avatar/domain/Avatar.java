package com.dnf.lookav.avatar.domain;

import com.dnf.lookav.avatar.dto.AvatarDto;

import jakarta.persistence.*;

import lombok.*;

import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avatar_id")
    private Long id;

    private String characterId;

    private String job;

    @Enumerated(EnumType.STRING)
    private Server server;

    @ColumnDefault("0")
    private Integer likes;

    private LocalDateTime registerDate;

    public static Avatar toEntity(AvatarDto avatarDto) {
        return Avatar.builder()
                .characterId(avatarDto.getCharacterId())
                .job(avatarDto.getJob())
                .server(Server.valueOf(avatarDto.getServerId()))
                .likes(avatarDto.getLikes())
                .registerDate(avatarDto.getRegisterDate())
                .build();
    }
}
