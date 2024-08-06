package com.dnf.lookav.avatar.domain;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
