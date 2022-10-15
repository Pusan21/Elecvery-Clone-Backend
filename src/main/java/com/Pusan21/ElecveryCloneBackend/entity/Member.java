package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

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
  private List<PaymentInformation> paymentInformations = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Reservation> reservations = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Member_Station> stations = new ArrayList<>();

}
