package com.erp.webtoon.controller;


import com.erp.webtoon.service.SlackService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SlackController {

    private final SlackService slackService;

    @ApiOperation(value = "슬랙 메시지 테스트")
    @GetMapping("/slack/{channel}")
    public void test(@PathVariable String channel) {

        log.info("슬랙 test 채널 테스트");

        slackService.sendSlackChannel("슬랙 메시지 테스트", channel);
    }
}
