package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.model.Board;
import com.sjkim.springboottransaction.model.History;
import org.springframework.stereotype.Component;

@Component
public class InitData {

    public Board buildBoard() {
        return Board.builder().title("TITLE").content("CONTENT").writer("WRITER").build();
    }

    public History buildHistory() {
        return History.builder().action("ACTION").build();
    }
}
