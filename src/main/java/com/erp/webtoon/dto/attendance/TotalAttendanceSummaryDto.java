package com.erp.webtoon.dto.attendance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@ApiModel(description = "실시간 전체 근태 현황 요약 DTO")
public class TotalAttendanceSummaryDto {

    @ApiModelProperty(value = "전체 직원수", example = "150")
    private Long totalUserCnt;

    @ApiModelProperty(value = "전체 직원 리스트")
    private List<TotalAttendanceUserListDto> totalUserList;

    @ApiModelProperty(value = "정상 출근 직원수", example = "120")
    private Long onTimeStartUserCnt;

    @ApiModelProperty(value = "정상 출근 직원 리스트")
    private List<TotalAttendanceUserListDto> onTimeStartUserList;

    @ApiModelProperty(value = "지각 출근 직원수", example = "20")
    private Long lateStartUserCnt;

    @ApiModelProperty(value = "지각 출근 직원 리스트")
    private List<TotalAttendanceUserListDto> lateStartUserList;

    @ApiModelProperty(value = "미출근 직원수", example = "10")
    private Long notStartUserCnt;

    @ApiModelProperty(value = "미출근 직원 리스트")
    private List<TotalAttendanceUserListDto> notStartUserList;

    @ApiModelProperty(value = "휴가 직원수", example = "5")
    private Long dayOffUserCnt;

    @ApiModelProperty(value = "휴가 직원 리스트")
    private List<TotalAttendanceUserListDto> dayOffUserList;

    @ApiModelProperty(value = "정상 퇴근 직원수", example = "130")
    private Long onTimeEndUserCnt;

    @ApiModelProperty(value = "정상 퇴근 직원 리스트")
    private List<TotalAttendanceUserListDto> onTimeEndUserList;

    @ApiModelProperty(value = "연장근무 직원수", example = "10")
    private Long notEndUserCnt;

    @ApiModelProperty(value = "연장근무 직원 리스트")
    private List<TotalAttendanceUserListDto> notEndUserList;

}
