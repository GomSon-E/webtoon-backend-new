package com.erp.webtoon.dto.itsm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@ApiModel(description = "코멘트 등록 응답 DTO")
public class CommentResponseDto {

    @ApiModelProperty(value = "메시지 ID", example = "1")
    private Long messageId;

    @ApiModelProperty(value = "발신일시", example = "2023-10-01")
    private LocalDate createdAt;
}
