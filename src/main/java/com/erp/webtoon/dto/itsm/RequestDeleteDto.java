package com.erp.webtoon.dto.itsm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(description = "요청 삭제 요청 DTO")
public class RequestDeleteDto {

    @ApiModelProperty(value = "요청 ID", example = "1")
    private Long requestId;

    @ApiModelProperty(value = "요청 ID 리스트", example = "[{ \"requestId : 1 \"}, { \"requestId : 2 \"}]")
    private List<RequestDeleteDto> requestIds;

}
