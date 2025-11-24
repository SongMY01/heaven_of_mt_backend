package com.heavenofmt.repository;

import com.heavenofmt.domain.CustomGame;
import com.heavenofmt.domain.CustomGameQuestion;
import com.heavenofmt.domain.Project;
import com.heavenofmt.domain.User;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class CustomGameQuestionRepositoryTest {

    @Autowired
    private CustomGameQuestionRepository questionRepository;

    @Autowired
    private CustomGameRepository gameRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testSaveQuestion() {
        // given
        User user = userRepository.save(User.builder()
                .name("문제유저")
                .email("quiz@test.com")
                .build());

        Project project = projectRepository.save(Project.builder()
                .title("문제 프로젝트")
                .user(user)
                .build());

        CustomGame game = gameRepository.save(CustomGame.builder()
                .gameType("객관식 퀴즈")
                .project(project)
                .user(user)
                .build());

        CustomGameQuestion question = CustomGameQuestion.builder()
                .game(game)
                .orderIndex(1)
                .questionText("스프링의 기본 어노테이션은?")
                .answer("Annotation")
                .build();

        // when
        CustomGameQuestion saved = questionRepository.save(question);

        // then
        Assertions.assertThat(saved.getGame().getId()).isEqualTo(game.getId());
        Assertions.assertThat(saved.getOrderIndex()).isEqualTo(1);
        Assertions.assertThat(saved.getQuestionText()).contains("스프링");
    }
}
