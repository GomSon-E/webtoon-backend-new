package com.erp.webtoon.dto.itsm;

import com.erp.webtoon.domain.RequestDt;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "요청 상세 목록 조회 응답 DTO")
public class RequestDtResponseDto {

    @ApiModelProperty(value = "요청 상세 내용", example = "노트북")
    private String content;

    @ApiModelProperty(value = "요청 개수", example = "2")
    private int count;

    @ApiModelProperty(value = "비용", example = "3000000")
    private int cost;

    public RequestDtResponseDto(RequestDt requestDt) {
        this.content = requestDt.getContent();
        this.count = requestDt.getCount();
        this.cost = requestDt.getCost();
    }
}
