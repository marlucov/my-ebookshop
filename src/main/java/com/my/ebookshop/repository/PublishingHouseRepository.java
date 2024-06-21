package com.my.ebookshop.repository;

import com.my.ebookshop.repository.entity.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, UUID> {
}
