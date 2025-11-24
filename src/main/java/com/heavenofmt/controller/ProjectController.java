package com.heavenofmt.controller;

import com.heavenofmt.domain.Project;
import com.heavenofmt.dto.ProjectDto;
import com.heavenofmt.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    // 프로젝트 생성
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(
            @RequestParam Long userId,
            @RequestParam String title
    ) {
        Project project = projectService.createProject(userId, title);
        return ResponseEntity.ok(ProjectDto.from(project));
    }

    // 특정 유저의 프로젝트 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectDto>> getProjectsByUser(@PathVariable Long userId) {
        List<ProjectDto> projects = projectService.getProjectsByUser(userId)
                .stream()
                .map(ProjectDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projects);
    }

    // 프로젝트 단건 조회
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long projectId) {
        Project project = projectService.getProject(projectId);
        return ResponseEntity.ok(ProjectDto.from(project));
    }

    // 프로젝트 수정
    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDto> updateProject(
            @PathVariable Long projectId,
            @RequestParam String newTitle
    ) {
        Project updated = projectService.updateProject(projectId, newTitle);
        return ResponseEntity.ok(ProjectDto.from(updated));
    }

    // 프로젝트 삭제
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long projectId,
            @RequestParam Long userId
    ) {
        projectService.deleteProject(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}
