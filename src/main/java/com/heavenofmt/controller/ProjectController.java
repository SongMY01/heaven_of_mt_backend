package com.heavenofmt.controller;

import com.heavenofmt.domain.Project;
import com.heavenofmt.dto.ProjectDto;
import com.heavenofmt.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "프로젝트 생성", description = "새로운 프로젝트를 생성합니다.")
    @ApiResponse(responseCode = "200", description = "프로젝트 생성 성공")
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto request) {
        return ResponseEntity.ok(projectService.createProject(request.getUserId(), request.getTitle()));
    }

    @Operation(summary = "프로젝트 전체 조회", description = "모든 프로젝트를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(projectService.getProjectsByUser(userId));
    }

    @Operation(summary = "특정 프로젝트 조회", description = "프로젝트 ID로 특정 프로젝트를 조회합니다.")
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProject(projectId));
    }

    @Operation(summary = "프로젝트 수정", description = "프로젝트 제목을 수정합니다.")
    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestParam String title) {
        return ResponseEntity.ok(projectService.updateProject(projectId, title));
    }

    @Operation(summary = "프로젝트 삭제", description = "프로젝트를 삭제합니다.")
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId, @RequestParam Long userId) {
        projectService.deleteProject(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}
