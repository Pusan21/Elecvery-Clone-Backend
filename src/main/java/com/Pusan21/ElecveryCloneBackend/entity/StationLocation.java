package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationLocation {

  @Id
  @GeneratedValue
  private long stationLocationNumber;

  @Nullable
  private String address;

  private double latitude;

  private double longitude;

  private long zcode;

  private long zscode;

  @OneToOne
  private Station station;
}
