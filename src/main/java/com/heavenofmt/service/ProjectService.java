package com.heavenofmt.service;

import com.heavenofmt.domain.Project;
import com.heavenofmt.domain.User;
import com.heavenofmt.dto.ProjectDto;
import com.heavenofmt.repository.ProjectRepository;
import com.heavenofmt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    // 프로젝트 생성
    public Project createProject(Long userId, String title) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Project project = Project.builder()
                .title(title)
                .user(user)
                .build();

        return projectRepository.save(project);
    }

    // 유저별 프로젝트 목록 조회
    @Transactional(readOnly = true)
    public List<Project> getProjectsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        return projectRepository.findByUser(user);
    }

    // 특정 프로젝트 조회
    @Transactional(readOnly = true)
    public Project getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다."));
    }

    // 프로젝트 수정
    public Project updateProject(Long projectId, String newTitle) {
        Project project = getProject(projectId);
        project = Project.builder()
                .id(project.getId())
                .title(newTitle)
                .user(project.getUser())
                .build();
        return projectRepository.save(project);
    }

    // 프로젝트 삭제
    public void deleteProject(Long projectId, Long userId) {
        Project project = getProject(projectId);
        if (!project.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인이 만든 프로젝트만 삭제할 수 있습니다.");
        }
        projectRepository.delete(project);
    }
}
