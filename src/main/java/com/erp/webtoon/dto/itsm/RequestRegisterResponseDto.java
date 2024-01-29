package com.erp.webtoon.dto.itsm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@ApiModel(description = "요청 등록 응답 DTO")
public class RequestRegisterResponseDto {

    @ApiModelProperty(value = "요청 ID", example = "1")
    private Long requestId;

    @ApiModelProperty(value = "등록일시", example = "2023-09-01T10:01:25")
    private LocalDateTime createdAt;

}
