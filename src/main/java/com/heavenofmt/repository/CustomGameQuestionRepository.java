package com.heavenofmt.repository;

import com.heavenofmt.domain.CustomGame;
import com.heavenofmt.domain.CustomGameQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomGameQuestionRepository extends JpaRepository<CustomGameQuestion, Long> {
    List<CustomGameQuestion> findByGame(CustomGame game);
}
