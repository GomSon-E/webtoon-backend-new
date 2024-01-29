package com.erp.webtoon.dto.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(description = "메시지 목록 조회 응답 DTO")
public class MessageListDto {

    @ApiModelProperty(value = "메시지 ID", example = "1")
    private Long messageId;

    @ApiModelProperty(value = "메세지 내용", example = "새로운 요청이 등록되었습니다.")
    private String content;

    @ApiModelProperty(value = "참조 ID", example = "1")
    private Long refId;

    @ApiModelProperty(value = "참조 프로그램 ID", example = "purchaseRequest")
    private String programId;

    @ApiModelProperty(value = "발신자 사번", example = "20200501")
    private String sendEmployeeId;

    @ApiModelProperty(value = "발신자명", example = "홍길동")
    private String sendName;

}
