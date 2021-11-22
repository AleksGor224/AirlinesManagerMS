package com.demo.airlinesmanager.models.dto.aircraftdtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(description = "Buy aircraft data transfer object")
public class BuyAircraftDto {
    @NotNull(message = "*/sellerIdAirlines is required")
    @NotBlank(message = "*/sellerIdAirlines is required")
    @Size(min = 1, max = 200)
    @ApiModelProperty(notes = "Unique identifier of seller airlines", example = "889043b0-216b-4a35-a045-f9b2e8274bfe", required = true)
    @Pattern(regexp = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$", message = "*/Invalid id format")
    private String sellerIdAirlines;

    @NotNull(message = "*/buyerIdAirlines is required")
    @NotBlank(message = "*/buyerIdAirlines is required")
    @Size(min = 1, max = 200)
    @ApiModelProperty(notes = "Unique identifier of buyer airlines", example = "03e5e28d-8db5-47aa-9338-fb40c2c08c20", required = true)
    @Pattern(regexp = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$", message = "*/Invalid id format")
    private String buyerIdAirlines;

    @NotNull(message = "*/airCraftId is required")
    @NotBlank(message = "*/airCraftId is required")
    @Pattern(regexp = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$", message = "*/Invalid id format")
    @ApiModelProperty(notes = "Unique identifier of buying aircraft", example = "c876ccae-118b-469c-8b94-ddf649e72e7c", required = true)
    private String airCraftId;
}
