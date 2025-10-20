package com.heavenofmt.service;

import com.heavenofmt.domain.CustomGame;
import com.heavenofmt.domain.User;
import com.heavenofmt.dto.CustomGameDto;
import com.heavenofmt.repository.CustomGameRepository;
import com.heavenofmt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomGameService {

    private final CustomGameRepository customGameRepository;
    private final UserRepository userRepository;

    // 게임 생성
    public CustomGame createGame(Long userId, CustomGameDto.CreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        CustomGame game = CustomGame.builder()
                .title(request.getTitle())
                .gameType(request.getGameType())
                .user(user)
                .build();

        return customGameRepository.save(game);
    }

    // 로그인한 유저의 전체 게임 목록 조회
    @Transactional(readOnly = true)
    public List<CustomGame> getGamesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        return customGameRepository.findByUser(user);
    }

    // 특정 게임 조회
    @Transactional(readOnly = true)
    public CustomGame getGame(Long gameId) {
        return customGameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));
    }

    // 게임 삭제
    public void deleteGame(Long gameId, Long userId) {
        CustomGame game = getGame(gameId);
        if (!game.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인이 만든 게임만 삭제할 수 있습니다.");
        }
        customGameRepository.delete(game);
    }
}
