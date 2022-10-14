package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {

  @Id
  @GeneratedValue
  private long stationNumber;

  private String stationName;

  private String registrationNumber;

  private boolean isActive;

  @OneToOne(mappedBy = "station")
  private StationLocation stationLocation;

<<<<<<< HEAD
    @OneToMany(mappedBy = "station")
    private List<Member_Station> members = new ArrayList<>();
=======
  @OneToMany(mappedBy = "station")
  private List<StationSlot> stationSlots = new ArrayList<>();

  @OneToMany(mappedBy = "station")
  private List<Manager> managers = new ArrayList<>();

  public boolean getIsActive() {
    return isActive;
  }
>>>>>>> 7ab37d49808cbbffa490bd14d90907014bf8ba2a
}
