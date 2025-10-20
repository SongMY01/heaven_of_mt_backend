package com.heavenofmt.controller;

import com.heavenofmt.domain.CustomGame;
import com.heavenofmt.dto.CustomGameDto;
import com.heavenofmt.dto.CustomGameQuestionDto;
import com.heavenofmt.dto.GameCreateRequest;
import com.heavenofmt.service.CustomGameService;
import com.heavenofmt.service.CustomGameQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class CustomGameController {

    private final CustomGameService customGameService;
    private final CustomGameQuestionService questionService;

    /**
     * 1️⃣ 게임 + 문제 전체 생성 (프론트에서 한 번에 요청)
     */
    @PostMapping("/full")
    public ResponseEntity<CustomGame> createFullGame(@RequestBody GameCreateRequest request) {

        // (1) 게임 생성
        CustomGame game = customGameService.createGame(
                request.getUserId(),
                new CustomGameDto.CreateRequest(request.getTitle(), request.getGameType())
        );

        // (2) 문제들 등록
        if (request.getQuestions() != null) {
            for (CustomGameQuestionDto.CreateRequest q : request.getQuestions()) {
                questionService.addQuestion(game.getId(), q);
            }
        }

        // (3) 완성된 게임 반환
        return ResponseEntity.ok(game);
    }
}
