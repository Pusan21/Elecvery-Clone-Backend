package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Mycar {

    @Id
    @GeneratedValue
    private long mycarNumber; //식별 아이디

    private String plateNumber; //차량 번호판 - 10허 0000

    private String carType; //차량 브랜드

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "mycar")
    private List<Reservation> reservations = new ArrayList<>();
}
