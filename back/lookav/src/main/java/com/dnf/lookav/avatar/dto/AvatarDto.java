package com.dnf.lookav.avatar.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvatarDto {
    private Long id;
    private String characterId;
    private String job;
    private String serverId;
    private Integer likes;
    private LocalDateTime registerDate;
}
