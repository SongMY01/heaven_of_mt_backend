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

    // ✅ 문제 생성
    public CustomGameQuestion createQuestion(Long gameId, CustomGameQuestionDto.CreateRequest request) {
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

    // ✅ 게임의 모든 문제 조회
    @Transactional(readOnly = true)
    public List<CustomGameQuestion> getQuestionsByGame(Long gameId) {
        CustomGame game = customGameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));
        return questionRepository.findByGame(game);
    }

    // ✅ 문제 수정
    public CustomGameQuestion updateQuestion(Long gameId, Long questionId, CustomGameQuestionDto.UpdateRequest request) {
        CustomGameQuestion question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("문제를 찾을 수 없습니다."));

        if (!question.getGame().getId().equals(gameId)) {
            throw new IllegalArgumentException("해당 게임에 속하지 않은 문제입니다.");
        }
        question.update(
                request.getQuestionText(),
                request.getAnswer(),
                request.getImageUrl(),
                request.getOrderIndex()
        );

        return questionRepository.save(question);
    }

    // ✅ 문제 삭제
    public void deleteQuestion(Long gameId, Long questionId) {
        CustomGameQuestion question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("문제를 찾을 수 없습니다."));

        if (!question.getGame().getId().equals(gameId)) {
            throw new IllegalArgumentException("해당 게임에 속하지 않은 문제입니다.");
        }

        questionRepository.delete(question);
    }
}
