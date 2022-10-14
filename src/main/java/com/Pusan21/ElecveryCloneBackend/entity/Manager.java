package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Entity
@Getter
public class Manager {

  @Id
  @GeneratedValue
  private long managerNumber;

  private String loginId;

  private String loginPassword;

  private String email;

  private String nickname;

  private String role;

  private ZonedDateTime createDateTime;

  private ZonedDateTime lastLoginDateTime;

  @ManyToOne
  private Station station;
}
