package com.demo.airlinesmanager.models.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Aircrafts")
public class AircraftEntity {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;

    @NotNull
    @Column(name = "MODEL")
    private String model;
    @NotNull
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "MONTH_IN_USE")
    private Integer monthInUse = 0;
    @NotNull
    @Column(name = "MAX_DISTANCE")
    private Integer maxDistance;

    @ManyToOne(optional = true, fetch = FetchType.LAZY, targetEntity = AirlineEntity.class)
    private AirlineEntity ownerCompany;
}
