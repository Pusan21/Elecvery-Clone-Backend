package com.Pusan21.ElecveryCloneBackend.entity;

import com.Pusan21.ElecveryCloneBackend.dto.MyCarDto.UpdateMyCarDto;
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
@AllArgsConstructor
@NoArgsConstructor
public class MyCar {

    @Id
    @GeneratedValue
    private long myCarNumber; //식별 아이디

    private String plateNumber; //차량 번호판 - 10허 0000

    private String carType; //차량 브랜드

    @ManyToOne
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "myCar")
    private List<Reservation> reservations = new ArrayList<>();

    public void updateMyCar(UpdateMyCarDto updateMyCarDto){
        this.plateNumber = updateMyCarDto.getPlateNumber();
        this.carType = updateMyCarDto.getCarType();
    }

}
