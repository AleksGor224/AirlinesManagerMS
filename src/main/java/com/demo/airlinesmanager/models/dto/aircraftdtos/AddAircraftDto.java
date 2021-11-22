package com.demo.airlinesmanager.models.dto.aircraftdtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(description = "Add aircraft data transfer object")
public class AddAircraftDto {
    @NotNull(message = "*/aircaftId is required")
    @NotBlank(message = "*/aircaftId is required")
    @Pattern(regexp = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$", message = "Invalid id format")
    @ApiModelProperty(notes = "Unique identifier of the aircraft. Two aircrafts can't have the same ids.", example = "c876ccae-118b-469c-8b94-ddf649e72e7c", required = true)
    private String aircraftId;

    @NotNull(message = "*/modelName is required")
    @NotBlank(message = "*/modelName is required")
    @Size(min = 1, max = 100)
    @ApiModelProperty(notes = "Aircraft model name", example = "Boeing 747-800", required = true)
    private String modelName;

    @NotNull(message = "*/price is required")
    @Min(0)
    @ApiModelProperty(notes = "Aircraft base price", example = "594023.422", required = true)
    private Double price;

    @NotNull(message = "*/monthInUse is required")
    @Min(0)
    @Max(1200)
    @ApiModelProperty(notes = "Number of month in use for current aircraft", example = "26", required = true)
    private Integer monthInUse;

    @NotNull(message = "*/maxDistance is required")
    @Min(0)
    @Max(100000)
    @ApiModelProperty(notes = "Max distance for urent aircraft in KM", example = "14550", required = true)
    private Integer maxDistance;
}
