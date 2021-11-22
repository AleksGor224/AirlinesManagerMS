package com.demo.airlinesmanager.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Airlines")
public class AirlineEntity {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;

    @Column(name = "AIRLINES_NAME")
    private String airlinesName;
    @Column(name = "BUDGET")
    private Double budget;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = LocationEntity.class)
    private LocationEntity homeLocation;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = AircraftEntity.class)
    private List<AircraftEntity> airCrafts;

}
