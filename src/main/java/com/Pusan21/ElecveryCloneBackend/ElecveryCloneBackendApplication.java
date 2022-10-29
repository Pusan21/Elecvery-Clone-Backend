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

      authorizationService.saveMember(new Member(null, "testUser", "password1", "user@gmail.com", "usertest", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
      authorizationService.saveMember(new Member(null, "testManager",  "password2", "manager@gmail.com", "managertest", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

      authorizationService.addRoleToMember("testUser", "ROLE_USER");
      authorizationService.addRoleToMember("testManager", "ROLE_MANAGER");

    };
  }

}
