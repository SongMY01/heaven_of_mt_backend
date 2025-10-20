package com.heavenofmt.repository;

import com.heavenofmt.domain.CustomGame;
import com.heavenofmt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomGameRepository extends JpaRepository<CustomGame, Long> {
    List<CustomGame> findByUser(User user);
}
