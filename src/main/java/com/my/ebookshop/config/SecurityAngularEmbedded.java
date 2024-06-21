package com.my.ebookshop.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityAngularEmbedded {
  public static void addSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers(WebResources.ENDPOINT_API_CATEGORY).permitAll();
        auth.requestMatchers(WebResources.ENDPOINT_ANGULAR_EMBEDDED_ASSETS).permitAll();
        auth.requestMatchers(WebResources.ENDPOINT_CATEGORY).permitAll();
        auth.requestMatchers(WebResources.ENDPOINT_HOME).permitAll();
        auth.requestMatchers(WebResources.ENDPOINT_INFO).permitAll();
        auth.requestMatchers(WebResources.ENDPOINT_API_LOGO).permitAll();
      })
    ;
  }
}
