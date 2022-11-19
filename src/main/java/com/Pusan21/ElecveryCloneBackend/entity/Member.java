package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
  public Member(String loginId, String loginPassword, String email, String nickname, ZonedDateTime createDateTime, ZonedDateTime lastLoginDateTime) {
    this.loginId = loginId;
    this.loginPassword = loginPassword;
    this.email = email;
    this.nickname = nickname;
    this.createDateTime = createDateTime;
    this.lastLoginDateTime = lastLoginDateTime;
  }

  @Column(unique = true)
  @Id
  @GeneratedValue
  private Long memberId;
  private String loginId;

  private String loginPassword;

  @Column(unique = true)
  private String email;

  private String nickname;
  @CreatedDate
  private ZonedDateTime createDateTime;
  private ZonedDateTime lastLoginDateTime;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
  private List<MyCar> myCars = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
  private List<PaymentInformation> paymentInformations = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Member_Station> stations = new ArrayList<>();

}
