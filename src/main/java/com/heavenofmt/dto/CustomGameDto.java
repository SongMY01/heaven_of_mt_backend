package com.heavenofmt.dto;

import lombok.Getter;
import lombok.Setter;

public class CustomGameDto {

    @Getter @Setter
    public static class CreateRequest {
        private String title;
        private String gameType;

        public CreateRequest(String title, String gameType) {
        }
    }

    @Getter @Setter
    public static class Response {
        private Long id;
        private String title;
        private String gameType;
        private Long userId;
    }
}
