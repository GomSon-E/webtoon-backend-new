package com.erp.webtoon.controller;

import com.erp.webtoon.dto.webtoon.WebtoonListResponseDto;
import com.erp.webtoon.dto.webtoon.WebtoonRequestDto;
import com.erp.webtoon.dto.webtoon.WebtoonResponseDto;
import com.erp.webtoon.service.FileService;
import com.erp.webtoon.service.WebtoonService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebtoonController {

    private final WebtoonService webtoonService;

    private final FileService fileService;

    /**
     * 웹툰 등록
     */
    @PostMapping("/webtoon")
    public Long save(@RequestBody WebtoonRequestDto dto) throws IOException {
        return webtoonService.save(dto);
    }

    /**
     * 등록된 모든 웹툰 조회
     */
    @GetMapping("/webtoon")
    public ResponseEntity showAll(@RequestParam("page") int page) {
        List<WebtoonListResponseDto> dtos = webtoonService.getAllWebtoon(page);

        return ResponseEntity.ok(dtos);
    }


    /**
     * 특정 웹툰 조회
     */
    @GetMapping("/webtoon/{webtoonId}")
    public ResponseEntity<Result> getWebtoon(@PathVariable("webtoonId") Long webtoonId) throws MalformedURLException {
        WebtoonResponseDto webtoon = webtoonService.getOneWebtoon(webtoonId);

        // 썸네일 이미지 조회
        UrlResource resource = new UrlResource("file:" + fileService.getFullPath(webtoon.getThumbnailFileName()));

        return ResponseEntity.ok(new Result(resource, webtoon));

    }

    /**
     * 제목 웹툰 검색
     */
    @GetMapping("/webtoon")
    public ResponseEntity showByTitle(@RequestParam("search") String title) {

        List<WebtoonListResponseDto> dtos = webtoonService.getTitleWebtoon(title);

        return ResponseEntity.ok(dtos);

    }

    /**
     * 작가 웹툰 검색
     */
    @GetMapping("/webtoon")
    public ResponseEntity showByArtist(@RequestParam("search") String artist) {

        List<WebtoonListResponseDto> dtos = webtoonService.getTitleWebtoon(artist);

        return ResponseEntity.ok(dtos);
    }

    /**
     * 카테고리 웹툰 검색
     */
    @GetMapping("/webtoon")
    public ResponseEntity showByCategory(@RequestParam("search") String category) {

        List<WebtoonListResponseDto> dtos = webtoonService.getTitleWebtoon(category);

        return ResponseEntity.ok(dtos);

    }

    /**
     * 키워드 웹툰 검색
     */
    @GetMapping("/webtoon")
    public ResponseEntity showByKeyword(@RequestParam("search") String keyword) {

        List<WebtoonListResponseDto> dtos = webtoonService.getTitleWebtoon(keyword);

        return ResponseEntity.ok(dtos);
    }

    /**
     * 회차 별 조회 -> 이미지만 조회 가능하도록,,,(추후 프론트랑 논의)
     */
    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable("fileName") String fileName) throws MalformedURLException {
        UrlResource resource = new UrlResource("file:" + fileService.getFullPath(fileName));

        return ResponseEntity.ok(resource);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T Resource;
        private T info;
    }
}
