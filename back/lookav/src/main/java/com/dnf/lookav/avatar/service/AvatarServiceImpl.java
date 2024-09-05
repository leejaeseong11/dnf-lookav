package com.dnf.lookav.avatar.service;

import com.dnf.lookav.avatar.domain.Avatar;
import com.dnf.lookav.avatar.domain.AvatarItem;
import com.dnf.lookav.avatar.domain.Item;
import com.dnf.lookav.avatar.domain.constatns.AvatarSlot;
import com.dnf.lookav.avatar.dto.AvatarDto;
import com.dnf.lookav.avatar.repository.AvatarItemRepository;
import com.dnf.lookav.avatar.repository.AvatarRepository;
import com.dnf.lookav.avatar.repository.ItemRepository;
import com.dnf.lookav.common.AwsS3;
import com.dnf.lookav.common.DnfApi;

import lombok.RequiredArgsConstructor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final DnfApi dnfApi;
    private final AwsS3 awsS3;
    private final AvatarRepository avatarRepository;
    private final AvatarItemRepository avatarItemRepository;
    private final ItemRepository itemRepository;

    @Override
    public Long saveAvatar(AvatarDto avatarDto) {
        // save avatar (character)
        JSONObject characterInfo =
                dnfApi.getCharacterAvatar(avatarDto.getServerId(), avatarDto.getCharacterId());
        Avatar findAvatar = avatarRepository.findByCharacterId(avatarDto.getCharacterId());
        boolean isExistAvatar = false;
        if (findAvatar != null) {
            AvatarDto updateAvatarDto = new AvatarDto();
            updateAvatarDto.setId(findAvatar.getId());
            updateAvatarDto.setCharacterId(findAvatar.getCharacterId());
            updateAvatarDto.setJob(findAvatar.getJob());
            updateAvatarDto.setServerId(findAvatar.getServer().toString());
            updateAvatarDto.setLikes(findAvatar.getLikes());
            updateAvatarDto.setRegisterDate(LocalDateTime.now());
            awsS3.uploadImage(avatarDto.getCharacterId(), avatarDto.getServerId());
            findAvatar = avatarRepository.save(Avatar.toEntity(updateAvatarDto));
            isExistAvatar = true;
        } else {
            avatarDto.setJob(characterInfo.getString("jobName"));
            avatarDto.setLikes(0);
            avatarDto.setRegisterDate(LocalDateTime.now());
            awsS3.uploadImage(avatarDto.getCharacterId(), avatarDto.getServerId());
            findAvatar = avatarRepository.save(Avatar.toEntity(avatarDto));
        }

        // sava item and avatar
        JSONArray avatars = characterInfo.getJSONArray("avatar");
        for (int i = 0; i < avatars.length(); i++) {
            String slotName = avatars.getJSONObject(i).getString("slotId").toLowerCase();
            if (slotName.equals("aurora")) { // except aurora slot
                continue;
            }

            // save item
            String itemName;
            boolean itemNameIsNull =
                    avatars.getJSONObject(i).getJSONObject("clone").isNull("itemName");
            String itemRarity;
            String itemId;
            if (itemNameIsNull) {
                itemName = avatars.getJSONObject(i).getString("itemName");
                itemRarity = avatars.getJSONObject(i).getString("itemRarity");
                itemId = avatars.getJSONObject(i).getString("itemId");
            } else {
                itemName = avatars.getJSONObject(i).getJSONObject("clone").getString("itemName");
                itemId = avatars.getJSONObject(i).getJSONObject("clone").getString("itemId");
                itemRarity = dnfApi.getItemRarity(itemId);
            }
            Item findItem = itemRepository.findByDnfItemId(itemId);
            if (findItem == null) {
                Item saveItem =
                        Item.builder()
                                .name(itemName)
                                .itemRarity(itemRarity)
                                .dnfItemId(itemId)
                                .job(findAvatar.getJob())
                                .build();
                itemRepository.save(saveItem);
                findItem = saveItem;
            }

            // save avatar item
            if (isExistAvatar) {
                AvatarItem findAvatarItem =
                        avatarItemRepository.findByAvatarAndSlot(
                                findAvatar, AvatarSlot.valueOf(slotName));
                AvatarItem updateAvatarItem =
                        AvatarItem.builder()
                                .id(findAvatarItem.getId())
                                .item(findItem)
                                .avatar(findAvatarItem.getAvatar())
                                .slot(findAvatarItem.getSlot())
                                .build();
                avatarItemRepository.save(updateAvatarItem);
            } else {
                AvatarItem avatarItem =
                        AvatarItem.builder()
                                .item(findItem)
                                .avatar(findAvatar)
                                .slot(AvatarSlot.valueOf(slotName))
                                .build();
                avatarItemRepository.save(avatarItem);
            }
        }

        // todo: save tag (find free llm)

        return null; // todo: return charater id
    }

    @Override
    public Page<AvatarDto> findAvatarList(Pageable pageable) {
        // todo
        return null;
    }
}
