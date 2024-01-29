package com.erp.webtoon.dto.attendance;

import com.erp.webtoon.domain.Attendance;
import com.erp.webtoon.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "출퇴근 등록 요청 DTO")
public class AttendanceRequestDto {

    @ApiModelProperty(value = "기준월", hidden = true)
    @Builder.Default
    private int attendMonth = LocalDate.now().getMonthValue();

    @ApiModelProperty(value = "기준일", hidden = true)
    @Builder.Default
    private String attendDate = LocalDate.now().toString();

    @ApiModelProperty(value = "근태 타입", required = true, allowableValues = "START, END")
    @NotBlank
    private String attendType;

    @ApiModelProperty(value = "직원 사번", required = true, example = "20200501")
    @NotBlank
    private String employeeId;

    public Attendance toEntity(User user) {
        return Attendance.builder()
                .attendMonth(attendMonth)
                .attendDate(attendDate)
                .attendType(attendType)
                .user(user)
                .build();
    }

}
