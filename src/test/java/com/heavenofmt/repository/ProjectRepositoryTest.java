package com.heavenofmt.repository;

import com.heavenofmt.domain.Project;
import com.heavenofmt.domain.User;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class ProjectRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testSaveProject() {
        // given
        User user = userRepository.save(User.builder()
                .name("테스트유저")
                .email("user@test.com")
                .build());

        Project project = Project.builder()
                .title("테스트 프로젝트")
                .user(user)
                .build();

        // when
        Project saved = projectRepository.save(project);

        // then
        Assertions.assertThat(saved.getUser().getId()).isEqualTo(user.getId());
        Assertions.assertThat(saved.getTitle()).isEqualTo("테스트 프로젝트");
    }
}
