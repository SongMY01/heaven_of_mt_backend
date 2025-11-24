package com.heavenofmt.repository;

import com.heavenofmt.domain.User;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(false) // false면 DB에 실제로 데이터 남음
    public void testUserSaveAndFind() {
        // given
        User user = User.builder()
                .name("테스트유저")
                .email("test@example.com")
                .build();

        // when
        User saved = userRepository.save(user);
        User found = userRepository.findById(saved.getId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // then
        Assertions.assertThat(found.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(found.getName()).isEqualTo("테스트유저");
        Assertions.assertThat(found).isEqualTo(saved); // 같은 영속성 컨텍스트 내 동일성 보장
    }
}
