package com.heavenofmt.repository;

import com.heavenofmt.domain.CustomGame;
import com.heavenofmt.domain.Project;
import com.heavenofmt.domain.User;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class CustomGameRepositoryTest {

    @Autowired
    private CustomGameRepository gameRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testSaveGame() {
        // given
        User user = userRepository.save(User.builder()
                .name("게임유저")
                .email("game@test.com")
                .build());

        Project project = projectRepository.save(Project.builder()
                .title("게임 프로젝트")
                .user(user)
                .build());

        CustomGame game = CustomGame.builder()
                .gameType("OX Quiz")
                .project(project)
                .user(user)
                .build();

        // when
        CustomGame saved = gameRepository.save(game);

        // then
        Assertions.assertThat(saved.getProject().getId()).isEqualTo(project.getId());
        Assertions.assertThat(saved.getUser().getId()).isEqualTo(user.getId());
        Assertions.assertThat(saved.getGameType()).isEqualTo("OX Quiz");
    }
}
