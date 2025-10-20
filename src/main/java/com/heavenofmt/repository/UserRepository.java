package com.heavenofmt.repository;

import com.heavenofmt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 유저 조회 (로그인 시 자주 사용)
    Optional<User> findByEmail(String email);

    // 이름으로 유저 조회 (선택적으로 사용)
    Optional<User> findByName(String name);

}
