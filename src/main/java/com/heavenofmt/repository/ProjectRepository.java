package com.heavenofmt.repository;

import com.heavenofmt.domain.Project;
import com.heavenofmt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUser(User user);
}
