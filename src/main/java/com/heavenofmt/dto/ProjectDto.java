package com.heavenofmt.dto;

import com.heavenofmt.domain.Project;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {

    private Long id;
    private String title;
    private Long userId;

    public static ProjectDto from(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .title(project.getTitle())
                .userId(project.getUser().getId())
                .build();
    }
}
