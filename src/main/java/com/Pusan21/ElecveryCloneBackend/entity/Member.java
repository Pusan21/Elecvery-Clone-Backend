package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
<<<<<<< HEAD:src/main/java/com/Pusan21/ElecveryCloneBackend/entity/Member.java
public class Member {
    @Id
    @GeneratedValue
    private long memberNumber;
=======
public class Customer {
>>>>>>> 7ab37d49808cbbffa490bd14d90907014bf8ba2a:src/main/java/com/Pusan21/ElecveryCloneBackend/entity/Customer.java

  @Id
  @GeneratedValue
  private long customerNumber;

  private String loginId;

  private String loginPassword;

  private String email;

  private String nickname;

  private String role;

  private ZonedDateTime createDateTime;

<<<<<<< HEAD:src/main/java/com/Pusan21/ElecveryCloneBackend/entity/Member.java
    @OneToMany(mappedBy = "member")
    private List<PaymentInformation> paymentInformations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Member_Station> stations = new ArrayList<>();
=======
  private ZonedDateTime lastLoginDateTime;

  @OneToMany(mappedBy = "customer")
  private List<CustomerPaymentInformation> customerPaymentInformations = new ArrayList<>();

  @OneToMany(mappedBy = "customer")
  private List<Reservation> reservations = new ArrayList<>();
>>>>>>> 7ab37d49808cbbffa490bd14d90907014bf8ba2a:src/main/java/com/Pusan21/ElecveryCloneBackend/entity/Customer.java
}
