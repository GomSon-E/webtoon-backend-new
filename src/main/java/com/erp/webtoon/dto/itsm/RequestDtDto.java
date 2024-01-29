package com.erp.webtoon.dto.itsm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "요청 상세 목록 등록 요청 DTO")
public class RequestDtDto {

    @ApiModelProperty(value = "요청 상세 내용", example = "노트북")
    private String content;

    @ApiModelProperty(value = "요청 개수", example = "2")
    private int count;

    @ApiModelProperty(value = "비용", example = "3000000")
    private int cost;
}
