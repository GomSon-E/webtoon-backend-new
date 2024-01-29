package com.erp.webtoon.dto.message;

import com.erp.webtoon.domain.Message;
import com.erp.webtoon.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ApiModel(description = "메시지 저장 요청 DTO")
public class MessageSaveDto {

    @ApiModelProperty(value = "메세지 타입", required = true, allowableValues = "ALL (전체 알림), WT (웹툰관리부 알림), IT (개발부 알림), DM (개인 메시지)")
    @NotBlank
    private String channel;

    @ApiModelProperty(value = "메세지 내용", required = true, example = "새로운 요청이 등록되었습니다.")
    @NotBlank
    private String content;

    @ApiModelProperty(value = "참조 ID", example = "1")
    private Long refId;

    @ApiModelProperty(value = "참조 프로그램 ID", example = "purchaseRequest")
    private String programId;

    @ApiModelProperty(value = "메시지 상태", hidden = true)
    private String stat;

    @ApiModelProperty(value = "수신자 사번", example = "20200501")
    private String rcvEmpId;

    @ApiModelProperty(value = "발신자 사번", example = "20200502")
    private String sendEmpId;

    public Message toEntity(User rcvUser, User sendUser) {
        return Message.builder()
                .msgType(channel)
                .content(content)
                .refId(refId)
                .programId(programId)
                .stat('Y')
                .rcvUser(rcvUser)
                .sendUser(sendUser)
                .build();
    }
}