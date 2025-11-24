package com.heavenofmt.controller;

import com.heavenofmt.domain.CustomGameQuestion;
import com.heavenofmt.dto.CustomGameQuestionDto;
import com.heavenofmt.service.CustomGameQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games/{gameId}/questions")
@RequiredArgsConstructor
public class CustomGameQuestionController {

    private final CustomGameQuestionService questionService;

    @Operation(summary = "문제 생성", description = "특정 게임에 문제를 추가합니다.")
    @ApiResponse(responseCode = "200", description = "문제 생성 성공")
    @PostMapping
    public ResponseEntity<CustomGameQuestion> createQuestion(
            @PathVariable Long gameId,
            @RequestBody CustomGameQuestionDto.CreateRequest request) {
        return ResponseEntity.ok(questionService.createQuestion(gameId, request));
    }

    @Operation(summary = "문제 목록 조회", description = "특정 게임의 모든 문제를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<CustomGameQuestion>> getQuestions(@PathVariable Long gameId) {
        return ResponseEntity.ok(questionService.getQuestionsByGame(gameId));
    }

    @Operation(summary = "문제 수정", description = "특정 문제를 수정합니다.")
    @PatchMapping("/{questionId}")
    public ResponseEntity<CustomGameQuestion> updateQuestion(
            @PathVariable Long gameId,
            @PathVariable Long questionId,
            @RequestBody CustomGameQuestionDto.UpdateRequest request) {
        return ResponseEntity.ok(questionService.updateQuestion(gameId, questionId, request));
    }

    @Operation(summary = "문제 삭제", description = "특정 문제를 삭제합니다.")
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(
            @PathVariable Long gameId,
            @PathVariable Long questionId) {
        questionService.deleteQuestion(gameId, questionId);
        return ResponseEntity.noContent().build();
    }
}
