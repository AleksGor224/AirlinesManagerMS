package com.demo.airlinesmanager.models.entities.pkeys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LocationEntityPK implements Serializable {
    private Double latitude;
    private Double longitude;
}
