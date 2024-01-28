package com.erp.webtoon.controller;

import com.erp.webtoon.dto.webtoon.FeedbackListDto;
import com.erp.webtoon.dto.webtoon.FeedbackSaveDto;
import com.erp.webtoon.dto.webtoon.WebtoonDtRequestDto;
import com.erp.webtoon.dto.webtoon.WebtoonDtResponseDto;
import com.erp.webtoon.dto.webtoon.WebtoonDtUpdateDto;
import com.erp.webtoon.service.FileService;
import com.erp.webtoon.service.WebtoonDtService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebtoonDtController {

    private final WebtoonDtService webtoonDtService;
    private final FileService fileService;

    @ApiOperation(value = "웹툰 회차 등록")
    @PostMapping("/webtoonDt")
    public ResponseEntity upload(@RequestPart WebtoonDtRequestDto dto, @RequestPart("thumbnailFile") MultipartFile thumbnailFile,
                                 @RequestPart("webtoonFile") MultipartFile webtoonFile) throws IOException {
        webtoonDtService.upload(dto, thumbnailFile, webtoonFile);

        String successMsg = "{ \"webtoonId\": "+ dto.getWebtoonId() +" }";

        return ResponseEntity.ok(successMsg);
    }

    @ApiOperation(value = "웹툰 회차 최종 업로드 처리")
    @PostMapping("/webtoonDt/{webtoonDtId}")
    public void uploadFinal(@PathVariable Long webtoonDtId) {
        webtoonDtService.finalUpload(webtoonDtId);
    }

    @ApiOperation(value = "웹툰 회차 개별 조회")
    @GetMapping("/webtoonDt/{webtoonDtId}")
    public ResponseEntity<Result> getImage(@PathVariable("webtoonDtId") Long webtoonDtId) throws MalformedURLException {
        WebtoonDtResponseDto dto = webtoonDtService.showOne(webtoonDtId);

        UrlResource resourceThumb = new UrlResource("http://146.56.98.153:8080" +fileService.getFullPath(dto.getThumbnailFileName()));
        UrlResource resourceEpisode = new UrlResource("http://146.56.98.153:8080" +fileService.getFullPath(dto.getEpisodeFileName()));

        return ResponseEntity.ok(new Result(resourceThumb.getURL(), resourceEpisode.getURL(), dto));
    }

    @ApiOperation(value = "웹툰 회차 정보 수정")
    @PutMapping("/webtoonDt/{webtoonDtId}")
    public ResponseEntity update(@PathVariable Long webtoonDtId, @RequestPart("dto") WebtoonDtUpdateDto dto,
                                 @RequestPart(value = "thumbnailFile", required = false) MultipartFile thumbnailFile,
                                 @RequestPart(value = "webtoonFile", required = false) MultipartFile webtoonFile) throws IOException {
        webtoonDtService.update(webtoonDtId, dto, thumbnailFile, webtoonFile);

        return ResponseEntity.ok(redirect(webtoonDtId));
    }

    @ApiOperation(value = "웹툰 회차 삭제")
    @DeleteMapping("/webtoonDt/{webtoonDtId}")
    public ResponseEntity delete(@PathVariable Long webtoonDtId) {
        webtoonDtService.delete(webtoonDtId);
        return ResponseEntity.ok(redirect(webtoonDtId));
    }

    @ApiOperation(value = "피드백 등록")
    @PostMapping ("/webtoonDt/feedBack")
    public List<FeedbackListDto> registerFeedBack(@RequestBody FeedbackSaveDto dto) throws IOException {
        webtoonDtService.addFeedbackMsg(dto);

        return webtoonDtService.findFeedbackList(dto.getRefId());
    }

    private URI redirect(Long webtoonDtId) {
        HttpHeaders headers = new HttpHeaders();
        URI location = UriComponentsBuilder.newInstance()
                .path("/webtoonDt/{webtoonDtId}")
                .buildAndExpand(webtoonDtId).toUri();
        return location;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T resourceThumb;
        private T resourceEpisode;
        private T info;
    }
}
