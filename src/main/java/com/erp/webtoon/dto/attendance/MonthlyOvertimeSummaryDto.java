package com.erp.webtoon.dto.attendance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "월별 연장근무시간 합계 응답 DTO")
public class MonthlyOvertimeSummaryDto {

    @ApiModelProperty(value = "1월 연장 근무 시간 합계", example = "120:30:00")
    private String janOvertime;

    @ApiModelProperty(value = "2월 연장 근무 시간 합계", example = "80:15:00")
    private String febOvertime;

    @ApiModelProperty(value = "3월 연장 근무 시간 합계", example = "110:45:00")
    private String marOvertime;

    @ApiModelProperty(value = "4월 연장 근무 시간 합계", example = "95:20:00")
    private String aprOvertime;

    @ApiModelProperty(value = "5월 연장 근무 시간 합계", example = "130:00:00")
    private String mayOvertime;

    @ApiModelProperty(value = "6월 연장 근무 시간 합계", example = "112:30:00")
    private String junOvertime;

    @ApiModelProperty(value = "7월 연장 근무 시간 합계", example = "125:45:00")
    private String julOvertime;

    @ApiModelProperty(value = "8월 연장 근무 시간 합계", example = "100:05:00")
    private String augOvertime;

    @ApiModelProperty(value = "9월 연장 근무 시간 합계", example = "115:30:00")
    private String sepOvertime;

    @ApiModelProperty(value = "10월 연장 근무 시간 합계", example = "92:40:00")
    private String octOvertime;

    @ApiModelProperty(value = "11월 연장 근무 시간 합계", example = "101:50:00")
    private String novOvertime;

    @ApiModelProperty(value = "12월 연장 근무 시간 합계", example = "105:10:00")
    private String devOvertime;

}
