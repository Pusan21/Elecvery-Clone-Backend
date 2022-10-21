package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInformation {

  @Id
  @GeneratedValue
  private long paymentInformationNumber;

  private String cardNumber;

  private String cardDetail;
  @ManyToOne
  private Member member;

  @OneToMany(mappedBy = "paymentInformation")
  private List<Reservation> reservations = new ArrayList<>();


  public void updatePaymentInformation(String cardNumber, String cardDetail) {
    this.cardNumber = cardNumber;
    this.cardDetail = cardDetail;
  }
}
