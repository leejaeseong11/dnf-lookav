package com.dnf.lookav.domain;

import jakarta.persistence.*;

import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Avatar {
    @Id
    @Column(name = "avatar_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String characterId;
    private int like;
    private LocalDateTime registerDate;
}
