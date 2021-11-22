package com.demo.airlinesmanager.models.entities;

import com.demo.airlinesmanager.models.entities.pkeys.LocationEntityPK;
import com.sun.istack.NotNull;
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
@Table(name = "Locations")
@IdClass(value = LocationEntityPK.class)
public class LocationEntity {

    @Id
    @NotNull
    @Column(name = "LATITUDE")
    private Double latitude;
    @Id
    @NotNull
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;
    @Column(name = "LOCATION_NAME")
    private String locationName = "Unknown point";

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = AirlineEntity.class)
    private List<AirlineEntity> homeLocationCompany;
}
