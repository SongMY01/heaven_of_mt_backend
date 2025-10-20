package com.heavenofmt.controller;

import com.heavenofmt.domain.CustomGameQuestion;
import com.heavenofmt.dto.CustomGameQuestionDto;
import com.heavenofmt.service.CustomGameQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games/{gameId}/questions")
public class CustomGameQuestionController {

    private final CustomGameQuestionService questionService;

    // 문제 등록
    @PostMapping
    public ResponseEntity<CustomGameQuestion> addQuestion(
            @PathVariable Long gameId,
            @RequestBody CustomGameQuestionDto.CreateRequest request) {
        return ResponseEntity.ok(questionService.addQuestion(gameId, request));
    }

    // 문제 목록 조회
    @GetMapping
    public ResponseEntity<List<CustomGameQuestion>> getQuestions(@PathVariable Long gameId) {
        return ResponseEntity.ok(questionService.getQuestions(gameId));
    }
}
