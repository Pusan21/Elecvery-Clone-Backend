package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Member_Station {
    @Id
    @GeneratedValue
    private long member_stationNumber;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Station station;
}
