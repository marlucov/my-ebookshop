package com.my.ebookshop;

import my_sql_password.MySqlPassword;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class MyEbookshop {
  public static void main(String[] args) {
    SpringApplicationRun(MyEbookshop.class, args);
  }

  private static ConfigurableApplicationContext SpringApplicationRun(Class<?> primarySource, String... args) {
    return SpringApplicationRun(new Class<?>[]{primarySource}, args);
  }

  private static ConfigurableApplicationContext SpringApplicationRun(Class<?>[] primarySources, String... args) {
    SpringApplication springApplication = new SpringApplication(primarySources);
    springApplication.setDefaultProperties(Map.of(
      "spring.datasource.password", MySqlPassword.get()
    ));
    return springApplication.run(args);
  }
}
