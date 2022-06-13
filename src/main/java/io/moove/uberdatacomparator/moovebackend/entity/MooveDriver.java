package io.moove.uberdatacomparator.moovebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
@Entity
@Table(name = "driver_driver")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MooveDriver {

    @Id
    private Integer id;
    private String name;
    private String phone;
    private String drn;
    private String email;
    @Column(name = "uber_id")
    private String uberId;
    @Column(name = "uber_name")
    private String uberName;
    @Column(name = "approval_status")
    private String approvalStatus;
    private String gender;
    @Column(name = "uber_status")
    private String uberStatus;
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Column(name = "updated_date")
    private LocalDate updatedDate;
    @Column(name = "country_id")
    private int countryId;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "city_id")
    private BigInteger cityId;
    @Column(name = "category_id")
    private BigInteger categoryId;
    @Column(name = "status_id")
    private BigInteger statusId;
    @Column(name = "primary_vehicle_id")
    private Integer primaryVehicleId;
    @Column(name = "secondary_vehicle_id")
    private Integer secondaryVehicleID;
    @Column(name = "sex_id")
    private Integer sexId;
}
