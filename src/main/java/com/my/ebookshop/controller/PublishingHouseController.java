package com.my.ebookshop.controller;

import com.my.ebookshop.config.WebResources;
import com.my.ebookshop.dto.PublishingHouseDto;
import com.my.ebookshop.service.PublishingHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublishingHouseController {
  @Autowired
  private PublishingHouseService publishingHouseService;

  @GetMapping(WebResources.ENDPOINT_API_PUBLISHING_HOUSE_ALL)
  public List<PublishingHouseDto> getDtoList() {
    return publishingHouseService.getDtoList();
  }
}
