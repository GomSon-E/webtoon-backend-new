package com.erp.webtoon.dto.attendance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(description = "전체 근태 조회 응답 DTO")
public class TotalAttendanceResponseDto {

    @ApiModelProperty(value = "실시간 전체 근태 현황 요약")
    private TotalAttendanceSummaryDto totalAttendanceSummaryDto;

    @ApiModelProperty(value = "월별 연장근무시간 합계")
    private MonthlyOvertimeSummaryDto monthlyOvertimeSummaryDto;

    @ApiModelProperty(value = "부서별 연장근무시간 합계")
    private DepartmentOvertimeSumDto departmentOvertimeSumDto;

    @ApiModelProperty(value = "부서별 연장근무시간 평균")
    private DepartmentOvertimeAvgDto departmentOvertimeAvgDto;

}
