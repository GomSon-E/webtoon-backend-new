package com.erp.webtoon.dto.itsm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(description = "코멘트 목록 조회 응답 DTO")
public class CommentListDto {

    @ApiModelProperty(value = "메세지 내용", example = "노트북 구매 요청")
    private String content;

    @ApiModelProperty(value = "발신자 부서명", example = "개발부")
    private String sendUserDeptName;

    @ApiModelProperty(value = "발신자 사번", example = "20200501")
    private String sendUserEmployeeId;

    @ApiModelProperty(value = "발신자 이름", example = "홍길동")
    private String sendUserName;
}
