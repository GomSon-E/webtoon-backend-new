package com.erp.webtoon.dto.attendance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "부서별 연장근무시간 평균 응답 DTO")
public class DepartmentOvertimeAvgDto {

    @ApiModelProperty(value = "인사부 연장근무시간 평균", example = "2:30:00")
    private String hrOvertimeAvg;

    @ApiModelProperty(value = "회계부 연장근무시간 평균", example = "2:00:00")
    private String amOvertimeAvg;

    @ApiModelProperty(value = "웹툰관리부 연장근무시간 평균", example = "1:45:00")
    private String wtOvertimeAvg;

    @ApiModelProperty(value = "개발부 연장근무시간 평균", example = "2:15:00")
    private String itOVertimeAvg;

}
