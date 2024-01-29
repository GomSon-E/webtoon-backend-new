package com.erp.webtoon.dto.file;

import com.erp.webtoon.domain.File;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "파일 조회 응답 DTO")
public class FileResponseDto {

    @ApiModelProperty(value = "파일 ID", example = "1")
    private Long fileId;

    @ApiModelProperty(value = "원본파일명", example = "example.png")
    private String fileOriginName;

    @ApiModelProperty(value = "파일 경로", example = "http://example:8080/example.png")
    private String filePath;

    public FileResponseDto(File file) {
        this.fileId = file.getId();
        this.fileOriginName = file.getOriginName();
        this.filePath = file.getFileName();
    }
}
