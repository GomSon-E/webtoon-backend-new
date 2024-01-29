package com.erp.webtoon.dto.itsm;

import com.erp.webtoon.dto.file.FileResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@ApiModel(description = "요청 개별 조회 응답 DTO")
public class RequestResponseDto {

    @ApiModelProperty(value = "요청 타입", example = "PURCHASE")
    private String reqType;

    @ApiModelProperty(value = "요청 제목", example = "노트북 구매 요청")
    private String title;

    @ApiModelProperty(value = "요청 내용", example = "개발부 신입사원 사무용 노트북 구매 요청 부탁드립니다.")
    private String content;

    @ApiModelProperty(value = "단계", example = "1")
    private int step;

    @ApiModelProperty(value = "기한 일자", example = "2023-09-01")
    private LocalDate dueDate;     // 기한 일자

    @ApiModelProperty(value = "완료 일자", example = "2023-09-03")
    private LocalDate doneDate;    // 완료 일자

    @ApiModelProperty(value = "요청자 사번", example = "20200501")
    private String reqUserId;   // 요청자 사번

    @ApiModelProperty(value = "개발부 담당자 사번", example = "20200501")
    private String itUserId;    // 담당자 사번

    @ApiModelProperty(value = "첨부 파일 목록")
    private List<FileResponseDto> files;

    @ApiModelProperty(value = "요청 상세 목록")
    private List<RequestDtResponseDto> requestDtList;

}
