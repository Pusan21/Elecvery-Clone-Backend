package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
public class StationLocation {

  @Id
  @GeneratedValue
  private long stationLocationNumber;

  private String address;

  private String detailedAddress;

  private double latitude;

  private double longitude;

  @OneToOne
  private Station station;
}
