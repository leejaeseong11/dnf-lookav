package com.dnf.lookav.avatar.dto;

import com.dnf.lookav.avatar.domain.Server;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvatarDto {
    private String characterId;
    private String job;
    private Server server;
    private Integer likes;
    private LocalDateTime registerDate;
    private String image;
}
