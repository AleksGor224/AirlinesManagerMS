package com.demo.airlinesmanager.models.dto.airlinesdtos;

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
@ApiModel(description = "Airlines data transfer object")
public class AirlineDto {
    @ApiModelProperty(notes = "Airlines name", example = "Pegasus")
    private String airlinesName;
    @ApiModelProperty(notes = "Home location name", example = "Frankfurt, Germany")
    private String homeLocationName;
    @ApiModelProperty(notes = "Current budget of current airlines", example = "2346246.2323")
    private Double budget;
    @ApiModelProperty(notes = "Airlines id", example = "c876ccae-118b-469c-8b94-ddf649e72e7c")
    private String airlinesId;
}
