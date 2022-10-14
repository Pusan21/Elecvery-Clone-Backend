package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
<<<<<<< HEAD:src/main/java/com/Pusan21/ElecveryCloneBackend/entity/PaymentInformation.java
public class PaymentInformation {
    @Id
    @GeneratedValue
    private long paymentInformationNumber;
=======
public class CustomerPaymentInformation {
>>>>>>> 7ab37d49808cbbffa490bd14d90907014bf8ba2a:src/main/java/com/Pusan21/ElecveryCloneBackend/entity/CustomerPaymentInformation.java

  @Id
  @GeneratedValue
  private long customerPaymentInformationNumber;

  private String cardNumber;

<<<<<<< HEAD:src/main/java/com/Pusan21/ElecveryCloneBackend/entity/PaymentInformation.java
    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "paymentInformation")
    private List<Reservation> reservations = new ArrayList<>();
=======
  private String cardDetail;

  @ManyToOne
  private Customer customer;

  @OneToMany(mappedBy = "customerPaymentInformation")
  private List<Reservation> reservations = new ArrayList<>();
>>>>>>> 7ab37d49808cbbffa490bd14d90907014bf8ba2a:src/main/java/com/Pusan21/ElecveryCloneBackend/entity/CustomerPaymentInformation.java
}
