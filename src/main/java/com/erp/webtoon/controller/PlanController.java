package com.erp.webtoon.controller;

import com.erp.webtoon.dto.plan.PlanListDto;
import com.erp.webtoon.dto.plan.PlanRequestDto;
import com.erp.webtoon.dto.plan.PlanResponseDto;
import com.erp.webtoon.dto.plan.PlanUpdateDto;
import com.erp.webtoon.service.PlanService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @ApiOperation(value = "일정 등록")
    @PostMapping("/plans")
    public ResponseEntity save(@RequestBody PlanRequestDto dto) {
        Long planId =  planService.save(dto);
        return ResponseEntity.ok(planId);
    }

    @ApiOperation(value = "일정 캘린더 조회")
    @GetMapping("/plans")
    public ResponseEntity showAll() {
        List<PlanListDto> plans = planService.getHomePlans();

        return ResponseEntity.ok(plans);
    }

    @ApiOperation(value = "일정 개별 조회")
    @GetMapping("/plans/{planId}")
    public ResponseEntity showOne(@PathVariable("planId") Long planId) {
        PlanResponseDto findPlan = planService.getPlan(planId);

        return ResponseEntity.ok(findPlan);
    }

    @ApiOperation(value = "일정 목록 조회")
    @GetMapping("/plans/list")
    public ResponseEntity getAll() {
        List<PlanResponseDto> plans = planService.getAllPlan();

        return ResponseEntity.ok(plans);
    }

    @ApiOperation(value = "일정 수정")
    @PutMapping("/plans/{planId}")
    public ResponseEntity update(@PathVariable("planId") Long planId, @RequestBody PlanUpdateDto dto) {
        planService.update(planId, dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "일정 삭제")
    @DeleteMapping("/plans/{planId}")
    public ResponseEntity delete(@PathVariable Long planId) {
        planService.delete(planId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
