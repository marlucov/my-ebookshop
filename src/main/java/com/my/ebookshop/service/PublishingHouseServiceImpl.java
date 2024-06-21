package com.my.ebookshop.service;

import com.my.ebookshop.dto.PublishingHouseDto;
import com.my.ebookshop.repository.PublishingHouseRepository;
import com.my.ebookshop.repository.entity.PublishingHouse;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PublishingHouseServiceImpl
  extends ServiceImpl<PublishingHouseRepository, PublishingHouseDto, PublishingHouse, UUID>
  implements PublishingHouseService {
  //
  @Override
  public PublishingHouseDto toDto(PublishingHouse publishingHouse) {
    PublishingHouseDto result = new PublishingHouseDto();
    result.setId(String.valueOf(publishingHouse.getId()));
    result.setName(publishingHouse.getName());
    result.setYearOfEstablishment(publishingHouse.getYearOfEstablishment());
    result.setContact(publishingHouse.getContact());
    return result;
  }

  @Override
  public PublishingHouse fromDto(PublishingHouseDto dto) {
    try {
      return getRepository().findById(UUID.fromString(dto.getId())).orElseThrow();
    }
    catch (IllegalArgumentException | NoSuchElementException exception) {
      PublishingHouse result = new PublishingHouse();
      result.setName(dto.getName());
      result.setYearOfEstablishment(dto.getYearOfEstablishment());
      result.setContact(dto.getContact());
      return result;
    }
  }
}
