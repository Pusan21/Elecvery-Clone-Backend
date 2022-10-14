package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class PaymentInformation {
    @Id
    @GeneratedValue
    private long paymentInformationNumber;

    private String cardNumber;

    private String cardDetail;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "paymentInformation")
    private List<Reservation> reservations = new ArrayList<>();
}
