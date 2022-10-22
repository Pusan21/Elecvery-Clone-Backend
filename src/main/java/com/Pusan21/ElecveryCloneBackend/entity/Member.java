package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Member {
  public Member(String loginId, String loginPassword, String email, String nickname, String role, ZonedDateTime createDateTime, ZonedDateTime lastLoginDateTime) {
    this.loginId = loginId;
    this.loginPassword = loginPassword;
    this.email = email;
    this.nickname = nickname;
    this.role = role;
    this.createDateTime = createDateTime;
    this.lastLoginDateTime = lastLoginDateTime;
  }

  @Id
  @GeneratedValue
  private long memberNumber;

  private String loginId;

  private String loginPassword;

  private String email;

  private String nickname;

  private String role;

  private ZonedDateTime createDateTime;

  private ZonedDateTime lastLoginDateTime;

  @OneToMany(mappedBy = "member")
  private List<Mycar> mycars = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<PaymentInformation> paymentInformations = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Member_Station> stations = new ArrayList<>();

}
