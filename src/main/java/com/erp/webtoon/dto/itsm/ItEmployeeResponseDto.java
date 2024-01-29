package com.erp.webtoon.dto.itsm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(description = "개발부 담당자 목록 조회 응답 DTO")
public class ItEmployeeResponseDto {

    @ApiModelProperty(value = "이름", example = "홍길동")
    private String name;

    @ApiModelProperty(value = "부서명", example = "개발부")
    private String deptName;

    @ApiModelProperty(value = "직급", example = "대리")
    private String position;

    @ApiModelProperty(value = "사번", example = "20200501")
    private String employeeId;

}
