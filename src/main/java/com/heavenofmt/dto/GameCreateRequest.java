package com.heavenofmt.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GameCreateRequest {

    private Long userId; // 만든 사람
    private String title; // 게임 제목
    private String gameType; // 게임 타입 (인물퀴즈, 초성퀴즈 등)

    private List<CustomGameQuestionDto.CreateRequest> questions; // 문제 리스트
}
