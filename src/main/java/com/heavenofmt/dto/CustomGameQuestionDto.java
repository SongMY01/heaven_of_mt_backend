package com.heavenofmt.dto;

import lombok.Getter;
import lombok.Setter;

public class CustomGameQuestionDto {

    @Getter @Setter
    public static class CreateRequest {
        private String questionText;
        private String answer;
        private String imageUrl;
        private Integer orderIndex;
    }

    @Getter @Setter
    public static class Response {
        private Long id;
        private String questionText;
        private String answer;
        private String imageUrl;
        private Integer orderIndex;
    }
}
