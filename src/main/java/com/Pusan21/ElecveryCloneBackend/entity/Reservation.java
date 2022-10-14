package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Entity
@Getter
public class Reservation {

  @Id
  @GeneratedValue
  private long reservationNumber;

  private ZonedDateTime startDateTime;

  private ZonedDateTime endDateTime;

  private String chargeOption;

<<<<<<< HEAD
    @ManyToOne
    private Member member;

    @ManyToOne
    private PaymentInformation paymentInformation;
=======
  @ManyToOne
  private StationSlot stationSlot;

  @ManyToOne
  private Customer customer;

  @ManyToOne
  private CustomerPaymentInformation customerPaymentInformation;
>>>>>>> 7ab37d49808cbbffa490bd14d90907014bf8ba2a
}
