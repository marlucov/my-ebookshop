package com.my.ebookshop.controller;

import com.my.ebookshop.config.WebResources;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
  @GetMapping({
    WebResources.ENDPOINT_CATEGORY,
    WebResources.ENDPOINT_HOME,
    WebResources.ENDPOINT_INFO
  })
  public String getIndexPage() {
    return WebResources.SOURCE_HOME;
  }

  @Getter
  public static class LogoPath {
    private final String src = WebResources.SOURCE_LOGO;
  }

  @GetMapping(WebResources.ENDPOINT_API_LOGO)
  @ResponseBody
  public LogoPath getLogoPath() {
    return new LogoPath();
  }
}
