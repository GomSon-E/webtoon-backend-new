package com.erp.webtoon.dto.itsm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(description = "요청 등록 요청 DTO")
public class RequestDto {

    @ApiModelProperty(value = "요청 타입", allowableValues = "PURCHASE, ASSIST")
    private String reqType;

    @ApiModelProperty(value = "요청 제목", example = "노트북 구매 요청")
    private String title;

    @ApiModelProperty(value = "요청 내용", example = "개발부 신입사원 사무용 노트북 구매 요청 부탁드립니다.")
    private String content;

    @ApiModelProperty(value = "요청 단계", allowableValues = "1 (요청), 2 (접수),  3 (진행), 4 (완료), 5 (반려)")
    private int step;

    @ApiModelProperty(value = "기한 일자", example = "2023-09-01")
    private LocalDate dueDate;

    @ApiModelProperty(value = "완료 일자", example = "2023-09-03")
    private LocalDate doneDate;

    @ApiModelProperty(value = "요청자 사번", example = "20200501")
    private String reqUserId;

    @ApiModelProperty(value = "개발부 담당자 사번", example = "20200501")
    private String itUserId;

    @ApiModelProperty(value = "요청 상세 목록")
    private List<RequestDtDto> requestDts;
}
