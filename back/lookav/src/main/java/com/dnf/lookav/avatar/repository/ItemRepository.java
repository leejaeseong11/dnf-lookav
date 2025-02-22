package com.dnf.lookav.avatar.repository;

import com.dnf.lookav.avatar.domain.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByDnfItemId(String dnfItemId);
}
