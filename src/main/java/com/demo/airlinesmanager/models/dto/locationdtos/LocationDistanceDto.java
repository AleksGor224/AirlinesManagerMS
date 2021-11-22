package com.demo.airlinesmanager.models.dto.locationdtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(description = "Distance data transfer object")
public class LocationDistanceDto {
    @ApiModelProperty(notes = "Location name", example = "Frankfurt, Germany")
    private String locationName;
    @ApiModelProperty(notes = "Distance in km", example = "352355.323")
    private Double distance;
}
