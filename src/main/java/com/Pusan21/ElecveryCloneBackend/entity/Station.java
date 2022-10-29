package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {

  @Id
  @GeneratedValue
  private long stationNumber;

  private String stationName;

  private String registrationNumber;

  private String useTime;

  private String availableChargeOption;

  private long output;

  private String method;

  private String note;

  private long stateUpdateDate;

  @OneToOne(mappedBy = "station" , cascade = CascadeType.ALL)
  private StationLocation stationLocation;

  @OneToMany(mappedBy = "station" , cascade = CascadeType.ALL)
  private List<StationSlot> stationSlots = new ArrayList<>();

  @OneToMany(mappedBy = "station")
  private List<Member_Station> members = new ArrayList<>();

  public void setStationLocation(StationLocation stationLocation){
    this.stationLocation = stationLocation;
    stationLocation.setStation(this);
  }
}
