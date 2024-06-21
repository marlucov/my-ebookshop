package com.my.ebookshop.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityLoginRegister {
  public static void addSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .formLogin(form -> form
        .loginPage(WebResources.ENDPOINT_LOGIN).permitAll()
        .defaultSuccessUrl(WebResources.ENDPOINT_API_HOME_AUTHENTICATED, true)
      )
      .logout(t -> t
        .logoutSuccessUrl(WebResources.ENDPOINT_HOME)
      )
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers(WebResources.ENDPOINT_FORGOT_PASSWORD).permitAll();
        auth.requestMatchers(WebResources.ENDPOINT_REGISTER).permitAll();
      })
    ;
  }
}
