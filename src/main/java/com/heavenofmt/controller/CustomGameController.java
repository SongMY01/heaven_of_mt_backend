package com.heavenofmt.controller;

import com.heavenofmt.domain.CustomGame;
import com.heavenofmt.dto.CustomGameDto;
import com.heavenofmt.service.CustomGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class CustomGameController {

    private final CustomGameService gameService;

    @Operation(summary = "게임 생성", description = "프로젝트 내에 새로운 게임을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "게임 생성 성공")
    @PostMapping
    public ResponseEntity<CustomGame> createGame(@RequestParam Long userId, @RequestBody CustomGameDto.CreateRequest request) {
        return ResponseEntity.ok(gameService.createGame(userId, request));
    }

    @Operation(summary = "프로젝트 내 게임 조회", description = "특정 프로젝트에 속한 모든 게임을 조회합니다.")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<CustomGame>> getGamesByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(gameService.getGamesByUser(projectId));
    }

    @Operation(summary = "특정 게임 조회", description = "게임 ID로 단일 게임을 조회합니다.")
    @GetMapping("/{gameId}")
    public ResponseEntity<CustomGame> getGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(gameService.getGame(gameId));
    }

    @Operation(summary = "게임 삭제", description = "특정 게임을 삭제합니다.")
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId, @RequestParam Long userId) {
        gameService.deleteGame(gameId, userId);
        return ResponseEntity.noContent().build();
    }
}
