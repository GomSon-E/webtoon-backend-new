package com.erp.webtoon.dto.attendance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "부서별 연장근무시간 합계 응답 DTO")
public class DepartmentOvertimeSumDto {

    @ApiModelProperty(value = "인사부 연장근무시간 합계", example = "220:15:00")
    private String hrOvertimeSum;

    @ApiModelProperty(value = "회계부 연장근무시간 합계", example = "180:40:00")
    private String amOvertimeSum;

    @ApiModelProperty(value = "웹툰관리부 연장근무시간 합계", example = "150:25:00")
    private String wtOvertimeSum;

    @ApiModelProperty(value = "개발부 연장근무시간 합계", example = "200:50:00")
    private String itOVertimeSum;

}
