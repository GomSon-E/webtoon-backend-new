package com.erp.webtoon.controller;

import com.erp.webtoon.dto.message.MessageListDto;
import com.erp.webtoon.service.MessageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @ApiOperation(value = "개인 메시지 조회")
    @GetMapping("/{employeeId}")
    public List<MessageListDto> getMessageList(@PathVariable String employeeId) {
        return messageService.getMessageList(employeeId);
    }

    @ApiOperation(value = "메시지 상태 변경")
    @PatchMapping("/{messageId}/{stat}")
    public void updateMessageStatus(@PathVariable Long messageId, @PathVariable char stat) {
        messageService.modifyStat(messageId, stat);
    }

}
