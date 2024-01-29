package com.erp.webtoon.dto.itsm;

import com.erp.webtoon.domain.Message;
import com.erp.webtoon.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "코멘트 등록 요청 DTO")
public class CommentSaveDto {

    @ApiModelProperty(value = "메시지 타입", hidden = true)
    private String msgType;

    @ApiModelProperty(value = "메시지 내용", required = true, example = "코멘트가 등록되었습니다.")
    @NotBlank
    private String content;

    @ApiModelProperty(value = "참조 ID", required = true, example = "1")
    @NotBlank
    private Long refId;

    @ApiModelProperty(value = "참조 프로그램 ID", required = true, example = "purchaseRequest")
    private String programId;

    @ApiModelProperty(value = "발신자 사번", required = true, example = "20200501")
    @NotBlank
    private String sendEmpId;

    public Message toEntity(User sendUser) {
        return Message.builder()
                .msgType("COMMENT")
                .content(content)
                .refId(refId)
                .programId(programId)
                .sendUser(sendUser)
                .build();
    }

}
