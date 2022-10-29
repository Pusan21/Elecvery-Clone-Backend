package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class StationSlot {

  @Id
  @GeneratedValue
  private long stationSlotNumber;

  private LocalTime openTime;

  private LocalTime closeTime;

  private long status;

  @ManyToOne
  private Station station;

  @OneToMany(mappedBy = "stationSlot")
  private List<Reservation> reservations = new ArrayList<>();
}
