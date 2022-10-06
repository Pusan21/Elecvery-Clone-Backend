package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Station {
    @Id
    @GeneratedValue
    private long stationNumber;

    private String stationName;

    private String registrationNumber;

    private boolean isActive;

    @OneToOne(mappedBy = "station")
    private StationLocation stationLocation;

    @OneToMany(mappedBy = "station")
    private List<StationSlot> stationSlots = new ArrayList<>();

    @OneToMany(mappedBy = "station")
    private List<Manager> managers = new ArrayList<>();

}
