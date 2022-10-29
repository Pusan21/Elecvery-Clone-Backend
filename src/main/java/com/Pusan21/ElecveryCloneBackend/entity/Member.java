package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  @Id
  @GeneratedValue
  private Long memberNumber;

  private String loginId;

  private String loginPassword;

  private String email;

  private String nickname;

//  private ZonedDateTime createDateTime;
//
//  private ZonedDateTime lastLoginDateTime;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Mycar> mycars = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<PaymentInformation> paymentInformations = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Member_Station> stations = new ArrayList<>();

}
