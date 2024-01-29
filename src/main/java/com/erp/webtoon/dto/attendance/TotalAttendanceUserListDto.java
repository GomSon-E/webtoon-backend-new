package com.erp.webtoon.dto.attendance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(description = "실시간 전체 근태 현황 직원 정보 목록 조회 응답 DTO")
public class TotalAttendanceUserListDto {

    @ApiModelProperty(value = "부서명", example = "회계부")
    private String deptName;

    @ApiModelProperty(value = "팀 번호", example = "1")
    private int teamNum;

    @ApiModelProperty(value = "직급", example = "대리")
    private String position;

    @ApiModelProperty(value = "사번", example = "20200501")
    private String employeeId;

    @ApiModelProperty(value = "이름", example = "홍길동")
    private String name;

    @ApiModelProperty(value = "전화번호", example = "010-1111-2222")
    private String tel;

    @ApiModelProperty(value = "이메일", example = "example@gmail.com")
    private String email;

}
