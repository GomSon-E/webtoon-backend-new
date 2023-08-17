package com.erp.webtoon.service;

import com.erp.webtoon.domain.Attendence;
import com.erp.webtoon.domain.User;
import com.erp.webtoon.dto.attendece.*;
import com.erp.webtoon.repository.AttendenceRepository;
import com.erp.webtoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendenceService {

    private final AttendenceRepository attendenceRepository;
    private final UserRepository userRepository;

    /*
        출근 & 퇴근
     */
    @Transactional
    public void addAttendence(AttendenceRequestDto dto) throws IOException {

        User user = userRepository.findByEmployeeId(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("해당 직원의 정보가 존재하지 않습니다."));

        Attendence attendence = dto.toEntity(user);
        attendenceRepository.save(attendence);

    }

    /*
        개인 근태 조회
     */
    public AttendenceResponseDto getIndividualAttendence(String employeeId) {

        User user = userRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("해당 직원의 정보가 존재하지 않습니다."));

        AttendenceResponseDto dto = new AttendenceResponseDto();

        dto.setWeeklyTotalTime(attendenceRepository.findIndividualWeeklyTotalTime(user.getId()));
        dto.setWeeklyOverTime(attendenceRepository.findIndividualWeeklyOverTime(user.getId()));
        dto.setMonthlyTotalTime(attendenceRepository.findIndividualMonthlyTotalTime(user.getId()));
        dto.setMonthlyOverTime(attendenceRepository.findIndividualMonthlyOverTime(user.getId()));
        dto.setAttendenceList(attendenceRepository.findIndividualAttendence(user));

        return dto;

    }

    /*
        전체 근태 조회
     */
    public TotalAttendenceResponseDto getTotalAttendence() {
        return TotalAttendenceResponseDto.builder()
                .totalAttendenceSummaryDto(getTotalAttendenceSummary())
                .monthlyOvertimeSummaryDto(getMonthlyOvertime())
                .departmentOvertimeSumDto(getDeptOverTimeSum())
                .departmentOvertimeAvgDto(getDeptOverTimeAvg())
                .build();
    }

    // 전체 근태 - 현황 요약 조회
    private TotalAttendenceSummaryDto getTotalAttendenceSummary() {

        return TotalAttendenceSummaryDto.builder()
                .totalUserCnt(userRepository.countAllBy())
                .onTimeStartUserCnt(getOnTimeStartAttendances().getCount())
                .lateStartUserCnt(getLateStartAttendances().getCount())
                .notStartUserCnt(getNotStartAttendances().getCount())
                .dayOffUserCnt(getDayOffAttendances().getCount())
                .onTimeEndUserCnt(getOnTimeEndAttendances().getCount())
                .notEndUserCnt(getNotEndAttendances().getCount())
                .build();

    }

    // 전체 근태 - 부서별 연장근무 시간 합계 조회
    private DepartmentOvertimeSumDto getDeptOverTimeSum() {

        return DepartmentOvertimeSumDto.builder()
                .hrOvertimeSum(getOverTimeSumByDepartment("HR"))
                .amOvertimeSum(getOverTimeSumByDepartment("AM"))
                .wtOvertimeSum(getOverTimeSumByDepartment("WT"))
                .itOVertimeSum(getOverTimeSumByDepartment("IT"))
                .build();

    }

    // 전체 근태 - 부서별 연장근무 시간 평균 조회
    private DepartmentOvertimeAvgDto getDeptOverTimeAvg() {

        return DepartmentOvertimeAvgDto.builder()
                .hrOvertimeAvg(getOverTimeAvgByDepartment("HR"))
                .amOvertimeAvg(getOverTimeAvgByDepartment("AM"))
                .wtOvertimeAvg(getOverTimeAvgByDepartment("WT"))
                .itOVertimeAvg(getOverTimeAvgByDepartment("IT"))
                .build();

    }


    // 전체 근태 - 월별 연장근무 시간 조회
    private MonthlyOvertimeSummaryDto getMonthlyOvertime() {
        return MonthlyOvertimeSummaryDto.builder()
                .janOvertime(calculateOvertimeByMonth(1))
                .febOvertime(calculateOvertimeByMonth(2))
                .marOvertime(calculateOvertimeByMonth(3))
                .aprOvertime(calculateOvertimeByMonth(4))
                .mayOvertime(calculateOvertimeByMonth(5))
                .junOvertime(calculateOvertimeByMonth(6))
                .junOvertime(calculateOvertimeByMonth(7))
                .augOvertime(calculateOvertimeByMonth(8))
                .sepOvertime(calculateOvertimeByMonth(9))
                .octOvertime(calculateOvertimeByMonth(10))
                .novOvertime(calculateOvertimeByMonth(11))
                .devOvertime(calculateOvertimeByMonth(12))
                .build();
    }

    // 근태 결과 클래스
    private static class AttendanceResult {
        private long count;
        private List<User> userList;

        public AttendanceResult() {
            this.count = 0;
            this.userList = Collections.emptyList();
        }

        public AttendanceResult(long count, List<User> userList) {
            this.count = count;
            this.userList = userList;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        public List<User> getUserList() {
            return userList;
        }

        public void setUserList(List<User> userList) {
            this.userList = userList;
        }

    }

    // 전체 - 정시 출근 직원 수
    private AttendanceResult getOnTimeStartAttendances() {
        String currentDate = LocalDate.now().toString();
        String attendType = "START";

        List<Attendence> attendances = attendenceRepository.findByAttendDateAndAttendType(currentDate, attendType);

        long count = attendances.stream()
                        .filter(this::isOnTime)
                        .count();

        List<User> userList = attendances.stream()
                                .filter(this::isOnTime)
                                .map(Attendence::getUser)
                                .collect(Collectors.toList());

        return new AttendanceResult(count, userList);
    }

    // 전체 - 지각 출근 직원 수
    private AttendanceResult getLateStartAttendances() {
        String currentDate = LocalDate.now().toString();
        String attendType = "START";

        List<Attendence> attendances = attendenceRepository.findByAttendDateAndAttendType(currentDate, attendType);

        long count = attendances.stream()
                        .filter(attendance -> !isOnTime(attendance))
                        .count();

        List<User> userList = attendances.stream()
                                .filter(attendance -> !isOnTime(attendance))
                                .map(Attendence::getUser)
                                .collect(Collectors.toList());

        return new AttendanceResult(count, userList);
    }

    // 전체 - 휴가 직원 수
    private AttendanceResult getDayOffAttendances() {
        String currentDate = LocalDate.now().toString();

        List<Attendence> attendances = attendenceRepository.findByAttendDateAndAttendType(currentDate, "DAYOFF");

        long count = attendances.size();
        List<User> userList = attendances.stream().map(Attendence::getUser).collect(Collectors.toList());

        return new AttendanceResult(count, userList);
    }

    // 전체 - 퇴근 직원 수
    private AttendanceResult getOnTimeEndAttendances() {
        String currentDate = LocalDate.now().toString();

        List<Attendence> attendances = attendenceRepository.findByAttendDateAndAttendType(currentDate, "END");

        long count = attendances.size();
        List<User> userList = attendances.stream().map(Attendence::getUser).collect(Collectors.toList());

        return new AttendanceResult(count, userList);
    }

    // 전체 - 연장 근무 (미퇴근) 직원 수
    private AttendanceResult getNotEndAttendances() {
        String currentDate = LocalDate.now().toString();
        LocalTime currentTime = LocalTime.now();

        if (currentTime.isAfter(LocalTime.of(18, 10))) {
            // 출근한 직원 (지각 포함)
            List<Attendence> startAttendances = attendenceRepository.findByAttendDateAndAttendType(currentDate, "START");
            if (startAttendances == null)  return new AttendanceResult();
            List<User> startAttendancesUserList = startAttendances.stream().map(Attendence::getUser).collect(Collectors.toList());

            long count = startAttendances.size() - getOnTimeEndAttendances().getCount();

            List<User> userList = new ArrayList<>(startAttendancesUserList);
            userList.removeAll(getOnTimeEndAttendances().getUserList());

            return new AttendanceResult(count, userList);
        }
        else {
            return new AttendanceResult();
        }
    }

    // 전체 - 미출근 직원 수
    private AttendanceResult getNotStartAttendances() {
        String currentDate = LocalDate.now().toString();

        // 전체 직원 수
        List<User> allUserList = userRepository.findAll();

        // 출근한 직원 수 (지각 포함)
        List<Attendence> startAttendances = attendenceRepository.findByAttendDateAndAttendType(currentDate, "START");
        if (startAttendances == null)  return new AttendanceResult(allUserList.size(), allUserList);
        List<User> startAttendancesUserList = startAttendances.stream().map(Attendence::getUser).collect(Collectors.toList());

        long count = allUserList.size() - startAttendances.size() - getDayOffAttendances().getCount();

        List<User> userList = new ArrayList<>(allUserList);
        userList.removeAll(startAttendancesUserList);
        userList.removeAll(getDayOffAttendances().getUserList());

        return new AttendanceResult(count, userList);
    }

    // 정시 출근 판단 함수 - 실제 출근 시간이 9시 10분 이전이면 true
    private boolean isOnTime(Attendence attendence) {
        LocalDateTime expectedStartTime = LocalDate.parse(attendence.getAttendDate()).atTime(9, 10);
        LocalDateTime actualStartTime = attendence.getAttendTime();

        return actualStartTime.isBefore(expectedStartTime);
    }

    // 이번달 부서별 연장 근무 합계 시간
    private String getOverTimeSumByDepartment(String deptCode) {

        int currentMonth = LocalDate.now().getMonthValue();
        String attendType = "END";
        List<User> userList = userRepository.findAllByDeptCode(deptCode);

        List<Attendence> attendances = attendenceRepository.findByAttendMonthAndAttendTypeAndUserIn(currentMonth, attendType, userList);


        return sumOvertime(attendances);
    }

    // 이번달 부서별 연장 근무 시간 평균 계산
    private String getOverTimeAvgByDepartment(String deptCode) {

        int currentMonth = LocalDate.now().getMonthValue();
        String attendType = "END";

        List<User> userList = userRepository.findAllByDeptCode(deptCode);
        List<Attendence> attendances = attendenceRepository.findByAttendMonthAndAttendTypeAndUserIn(currentMonth, attendType, userList);

        if (attendances.isEmpty())  return "00:00:00";

        Duration totalOverTime = attendances.stream()
                .map(this::calculateOverTime)
                .reduce(Duration.ZERO, Duration::plus);

        long totalMinutes = totalOverTime.toMinutes();
        int numEmployees = userList.size();

        long averageMinutes = totalMinutes / numEmployees;

        long avgHours = averageMinutes / 60;
        long avgMinutesRemaining = averageMinutes % 60;

        return String.format("%02d:%02d:00", avgHours, avgMinutesRemaining);
    }

    // 월별 연장근무 시간
    private String calculateOvertimeByMonth(int month) {
        List<Attendence> attendences = attendenceRepository.findByAttendMonthAndAttendType(month, "END");

        return sumOvertime(attendences);
    }

    // 연장근무 시간 계산
    private Duration calculateOverTime(Attendence attendence) {
        LocalDateTime expectedEndTime = LocalDate.parse(attendence.getAttendDate()).atTime(18, 0);
        LocalDateTime actualEndTime = attendence.getAttendTime();

        return Duration.between(expectedEndTime, actualEndTime);
    }

    // 연장근무 시간 합계
    private String sumOvertime(List<Attendence> attendances) {

        if (attendances.isEmpty())  return "00:00:00";

        Duration totalOverTime = attendances.stream()
                .map(this::calculateOverTime)
                .reduce(Duration.ZERO, Duration::plus);

        long totalHours = totalOverTime.toHours();
        long totalMinutes = totalOverTime.toMinutesPart();
        long totalSeconds = totalOverTime.toSecondsPart();

        return String.format("%02d:%02d:%02d", totalHours, totalMinutes, totalSeconds);

    }
}
