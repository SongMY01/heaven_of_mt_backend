package com.heavenofmt.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "custom_game_question")
public class CustomGameQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionText;

    @Column(nullable = false)
    private String answer;

    private String imageUrl; // 문제 이미지 경로

    private Integer orderIndex; // 순서

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private CustomGame game;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
