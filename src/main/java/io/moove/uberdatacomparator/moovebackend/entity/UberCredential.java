package io.moove.uberdatacomparator.moovebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver_ubercredential")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UberCredential {

    @Id
    private Integer id;
    private String drn;
    @Column(name = "driver_id")
    private String driverId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    private String picture;
    private double rating;
    @Column(name = "activation_status")
    private String activationStatus;
    @Column(name = "access_token")
    private String accessToken;
    @Column(name = "token_type")
    private String tokenType;
    @Column(name = "expires_in")
    private Integer expiresIn;
    @Column(name = "refresh_token")
    private String refreshToken;
    private String scope;
    @Column(name = "auth_date")
    private LocalDateTime authDate;
}
