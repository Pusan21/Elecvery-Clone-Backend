package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class CustomerPaymentInformation {
    @Id
    @GeneratedValue
    private long customerPaymentInformationNumber;

    private String cardNumber;

    private String cardDetail;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "customerPaymentInformation")
    private List<Reservation> reservations = new ArrayList<>();
}
