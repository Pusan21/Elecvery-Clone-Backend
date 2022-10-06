package com.Pusan21.ElecveryCloneBackend.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue
    private long userNumber;

    private String loginId;

    private String loginPassword;

    private String email;

    private String nickname;

    private String role;

    private ZonedDateTime createDateTime;

    private ZonedDateTime lastLoginDateTime;

    @OneToMany(mappedBy = "user")
    private List<UserPaymentInformation> userPaymentInformations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();
}
