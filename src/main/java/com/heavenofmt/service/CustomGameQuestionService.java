package com.heavenofmt.service;

import com.heavenofmt.domain.CustomGame;
import com.heavenofmt.domain.CustomGameQuestion;
import com.heavenofmt.dto.CustomGameQuestionDto;
import com.heavenofmt.repository.CustomGameQuestionRepository;
import com.heavenofmt.repository.CustomGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomGameQuestionService {

    private final CustomGameRepository customGameRepository;
    private final CustomGameQuestionRepository questionRepository;

    // 문제 등록
    public CustomGameQuestion addQuestion(Long gameId, CustomGameQuestionDto.CreateRequest request) {
        CustomGame game = customGameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));

        CustomGameQuestion question = CustomGameQuestion.builder()
                .questionText(request.getQuestionText())
                .answer(request.getAnswer())
                .imageUrl(request.getImageUrl())
                .orderIndex(request.getOrderIndex())
                .game(game)
                .build();

        return questionRepository.save(question);
    }

    // 게임의 모든 문제 조회
    @Transactional(readOnly = true)
    public List<CustomGameQuestion> getQuestions(Long gameId) {
        CustomGame game = customGameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));
        return questionRepository.findByGame(game);
    }
}
