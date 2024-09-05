package com.dnf.lookav.avatar.dto;

import lombok.Data;

@Data
public class AvatarItemDto {
    private String itemName;
    private String itemRarity;
    private String slot;
    private String status;
    private int price;
}
