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

  @ManyToOne
  private StationSlot stationSlot;

  @ManyToOne
  private Member member;

  @ManyToOne
  private PaymentInformation paymentInformation;

}
