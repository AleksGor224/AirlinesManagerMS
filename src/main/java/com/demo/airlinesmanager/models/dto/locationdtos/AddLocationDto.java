package com.demo.airlinesmanager.models.dto.locationdtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(description = "Add location data transfer object")
public class AddLocationDto {

    @NotNull(message = "*/homeLocationName is required")
    @NotBlank(message = "*/homeLocationName is required")
    @ApiModelProperty(notes = "Home location name", example = "Frankfurt, Germany", required = true)
    private String homeLocationName;

    @NotNull(message = "*/latitude is required")
    @ApiModelProperty(notes = "Latitude of home location", example = "50.0379326", required = true)
    @Min(value = -90, message = "*/Latitude must be in range [-180,180]")
    @Max(value = 90, message = "*/Latitude must be in range [-180,180]")
    private String latitude;

    @NotNull(message = "*/longitude is required")
    @ApiModelProperty(notes = "Longitude of home location", example = "8.5621518", required = true)
    @Min(value = -180, message = "*/Longitude must be in range [-180,180]")
    @Max(value = 180, message = "*/Longitude must be in range [-180,180]")
    private String longitude;
}
