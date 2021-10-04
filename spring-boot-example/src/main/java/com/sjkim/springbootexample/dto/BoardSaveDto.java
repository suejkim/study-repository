package com.sjkim.springbootexample.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardSaveDto {

    private String title;
    private String content;
    private String writer; // 원래는 인증할 때 처리

    @Builder
    public BoardSaveDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
