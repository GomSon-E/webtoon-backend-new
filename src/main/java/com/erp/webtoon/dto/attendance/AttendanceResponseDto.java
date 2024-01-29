package com.erp.webtoon.dto.attendance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "개인 근태 조회 응답 DTO")
public class AttendanceResponseDto {

    @ApiModelProperty(value = "이번주 누적 근무 시간", example = "40:00:00")
    private String weeklyTotalTime;

    @ApiModelProperty(value = "이번주 초과 근무 시간", example = "5:00:00")
    private String weeklyOverTime;

    @ApiModelProperty(value = "이번달 누적 근무 시간", example = "200:00:00")
    private String monthlyTotalTime;

    @ApiModelProperty(value = "이번달 초과 근무 시간", example = "14:00:00")
    private String monthlyOverTime;

    @ApiModelProperty(value = "개인 근태 내역")
    private List<IndividualAttendanceListDto> attendanceList;

}
