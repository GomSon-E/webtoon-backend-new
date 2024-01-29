package com.erp.webtoon.dto.attendance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(description = "개인 근태 내역 응답 DTO")
public interface IndividualAttendanceListDto {

    @ApiModelProperty(value = "주차", example = "1")
    int getWeek();

    @ApiModelProperty(value = "기준일", example = "2023-08-01")
    String getAttendDate();

    @ApiModelProperty(value = "시작시간", example = "2023-08-01T09:00:00")
    LocalDateTime getStartTime();

    @ApiModelProperty(value = "종료시간", example = "2023-08-01T18:00:00")
    LocalDateTime getEndTime();

    @ApiModelProperty(value = "총 근무시간", example = "9")
    String getTotalTime();

}
