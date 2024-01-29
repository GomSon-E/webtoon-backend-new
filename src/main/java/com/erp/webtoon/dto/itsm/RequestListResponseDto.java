package com.erp.webtoon.dto.itsm;

import com.erp.webtoon.domain.Request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(description = "요청 목록 조회 응답 DTO")
public class RequestListResponseDto {

    @ApiModelProperty(value = "요청 ID", example = "1")
    private Long reqId;

    @ApiModelProperty(value = "요청 타입", example = "PURCHASE")
    private String reqType;

    @ApiModelProperty(value = "요청 제목", example = "노트북 구매 요청")
    private String title;

    @ApiModelProperty(value = "단계", example = "1")
    private int step;

    @ApiModelProperty(value = "기한 일자", example = "2023-09-01")
    private LocalDate dueDate;

    @ApiModelProperty(value = "완료 일자", example = "2023-09-03")
    private LocalDate doneDate;

    @ApiModelProperty(value = "요청자 사번", example = "20200501")
    private String reqUser;

    @ApiModelProperty(value = "개발부 담당자 사번", example = "20200501")
    private String itUser;

    public RequestListResponseDto(Request request) {
        this.reqId = request.getId();
        this.reqType = request.getReqType();
        this.title = request.getTitle();
        this.step = request.getStep();
        this.dueDate = request.getDueDate();
        this.doneDate = request.getDoneDate();
        this.reqUser = request.getReqUser().getEmployeeId();
        this.itUser = request.getItUser().getEmployeeId();
    }
}
