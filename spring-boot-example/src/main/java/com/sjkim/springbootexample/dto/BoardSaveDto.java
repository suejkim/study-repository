package com.sjkim.springbootexample.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardSaveDto {

    private String title;
    private String content;
    private String writer;

}
