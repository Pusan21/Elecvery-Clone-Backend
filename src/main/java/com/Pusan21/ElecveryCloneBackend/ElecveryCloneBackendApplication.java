package com.Pusan21.ElecveryCloneBackend;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.Role;
import com.Pusan21.ElecveryCloneBackend.service.AuthorizationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@SpringBootApplication
public class ElecveryCloneBackendApplication {


  public static void main(String[] args) {
    SpringApplication.run(ElecveryCloneBackendApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner run(AuthorizationService authorizationService){
    return args -> {
      authorizationService.saveRole(new Role(null, "ROLE_USER"));
      authorizationService.saveRole(new Role(null, "ROLE_MANAGER"));

      ZonedDateTime date1 = ZonedDateTime.of(2001, 1, 1, 3, 12, 12, 12, ZoneId.of("Asia/Calcutta"));
      ZonedDateTime date2 = date1.plus(30, ChronoUnit.DAYS);
      authorizationService.saveMember(new Member(null, "testUser", "password1", "user@gmail.com", "usertest", date1, date2, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

      ZonedDateTime date3 = ZonedDateTime.of(2021, 5, 13, 10, 22, 3, 59, ZoneId.of("Asia/Seoul"));
      ZonedDateTime date4 = date3.plus(10, ChronoUnit.SECONDS);
      authorizationService.saveMember(new Member(null, "testManager",  "password2", "manager@gmail.com", "managertest", date3, date4, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

      authorizationService.addRoleToMember("testUser", "ROLE_USER");
      authorizationService.addRoleToMember("testManager", "ROLE_MANAGER");

    };
  }

}
