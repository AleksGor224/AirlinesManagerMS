package com.demo.airlinesmanager.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@ApiModel(description = "Service response data transfer object")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponseDto {
    @ApiModelProperty(notes = "Response code", example = "0")
    private Integer responseCode;
    @ApiModelProperty(notes = "Response description", example = "Success")
    private String responseDesc;
    @ApiModelProperty(notes = "Data object")
    private Object data;
}
