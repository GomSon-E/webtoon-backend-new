package com.erp.webtoon.dto.itsm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "요청 단계 변경 요청 DTO")
public class RequestStepDto {

    @ApiModelProperty(value = "요청 단계", allowableValues = "1 (요청), 2 (접수),  3 (진행), 4 (완료), 5 (반려)")
    private int step;

}
