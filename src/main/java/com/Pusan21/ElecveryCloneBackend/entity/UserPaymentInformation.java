package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class UserPaymentInformation {
    @Id
    @GeneratedValue
    private long userPaymentInformationNumber;

    private String cardNumber;

    private String cardDetails;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "userPaymentInformation")
    private List<Reservation> reservations = new ArrayList<>();
}
