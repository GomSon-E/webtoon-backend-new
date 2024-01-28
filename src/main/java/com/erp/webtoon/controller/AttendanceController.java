package com.erp.webtoon.controller;

import com.erp.webtoon.dto.attendance.AttendanceRequestDto;
import com.erp.webtoon.dto.attendance.AttendanceResponseDto;
import com.erp.webtoon.dto.attendance.TotalAttendanceResponseDto;
import com.erp.webtoon.service.AttendanceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @ApiOperation(value = "출퇴근 등록")
    @PostMapping
    public void addAttendanceRecord(@RequestBody AttendanceRequestDto dto) {
        attendanceService.addAttendance(dto);
    }

    @ApiOperation(value = "개인 근태 조회")
    @GetMapping("/{employeeId}")
    public AttendanceResponseDto getIndividualAttendance(@PathVariable String employeeId) {
        return attendanceService.getIndividualAttendance(employeeId);
    }

    @ApiOperation(value = "전체 근태 조회")
    @GetMapping("/total")
    public TotalAttendanceResponseDto getTotalAttendance() {
        return attendanceService.getTotalAttendance();
    }
}
