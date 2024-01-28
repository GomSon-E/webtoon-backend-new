package com.erp.webtoon.controller;


import com.erp.webtoon.dto.itsm.CommentSaveDto;
import com.erp.webtoon.dto.itsm.ItEmployeeResponseDto;
import com.erp.webtoon.dto.itsm.RequestListResponseDto;
import com.erp.webtoon.dto.itsm.RequestResponseDto;
import com.erp.webtoon.dto.itsm.RequestStepDto;
import com.erp.webtoon.dto.itsm.CommentListDto;
import com.erp.webtoon.dto.itsm.CommentResponseDto;
import com.erp.webtoon.dto.itsm.RequestDeleteDto;
import com.erp.webtoon.dto.itsm.RequestDto;
import com.erp.webtoon.dto.itsm.RequestRegisterResponseDto;
import com.erp.webtoon.service.RequestService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @ApiOperation(value = "장비 구매 요청")
    @PostMapping("/purchase-request")
    public RequestRegisterResponseDto purchaseRequest(@RequestPart("dto") RequestDto requestDto, @RequestPart(value = "files", required = false)List<MultipartFile> files) throws Exception {
        return requestService.purchaseRequest(requestDto, files);
    }

    @ApiOperation(value = "업무 지원 요청")
    @PostMapping("/request")
    public RequestRegisterResponseDto Request(@RequestPart("dto") RequestDto requestDto, @RequestPart(value = "files", required = false)List<MultipartFile> files) throws Exception {
        return requestService.assistRequest(requestDto, files);
    }

    @ApiOperation(value = "개발부 담당자 조회")
    @GetMapping("/IT-manager")
    public List<ItEmployeeResponseDto> findItManager() {
        return requestService.searchItEmployee();
    }

    @ApiOperation(value = "요청 삭제")
    @DeleteMapping("/request")
    public ResponseEntity deleteRequest(@RequestBody List<RequestDeleteDto> requestIds) {
        requestService.deleteRequest(requestIds);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "코멘트 등록")
    @PostMapping ("/comment")
    public CommentResponseDto registerComment(@RequestBody CommentSaveDto commentSaveDto) throws IOException {
        return requestService.registerComment(commentSaveDto);
    }

    @ApiOperation(value = "코멘트 목록 조회")
    @GetMapping("/comment")
    public List<CommentListDto> getAllComments(@RequestParam("requestId") Long requestId) {
        return requestService.getAllComments(requestId);
    }

    @ApiOperation(value = "요청 개별 조회")
    @GetMapping("/request/{requestId}")
    public ResponseEntity showOne(@PathVariable Long requestId) {
        RequestResponseDto request = requestService.search(requestId);

        return ResponseEntity.ok(request);
    }

    @ApiOperation(value = "사원별 요청 목록 조회")
    @GetMapping("/request/list/{employeeId}")
    public ResponseEntity showUserReqList(@PathVariable String employeeId) {
        List<RequestListResponseDto> requestList = requestService.searchUserList(employeeId);
        return ResponseEntity.ok(requestList);
    }

    @ApiOperation(value = "요청 전체 조회")
    @GetMapping("/request/all/{employeeId}")
    public ResponseEntity showAllList(@PathVariable String employeeId) throws IllegalAccessException {
        List<RequestListResponseDto> requestList = requestService.searchAllList(employeeId);
        return ResponseEntity.ok(requestList);
    }

    @ApiOperation(value = "요청 단계 변경")
    @PutMapping("/request/step/{requestId}")
    public ResponseEntity changeStep(@PathVariable Long requestId, @RequestBody RequestStepDto dto) {
        requestService.changeStep(requestId, dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "코멘트 삭제")
    @DeleteMapping("/comment")
    public ResponseEntity deleteComment(@RequestParam("messageId") Long messageId) {
        requestService.deleteComment(messageId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
