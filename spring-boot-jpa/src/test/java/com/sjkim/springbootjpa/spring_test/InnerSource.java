package com.sjkim.springbootjpa.spring_test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class InnerSource {
    private String inName;

    public InnerSource(String inName) {
        this.inName = inName;
    }

    public InnerSource changeInnerSource(String inName) {
        this.inName = inName;
        return this;
    }
}
