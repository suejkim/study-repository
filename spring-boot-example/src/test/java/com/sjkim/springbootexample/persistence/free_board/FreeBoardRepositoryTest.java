package com.sjkim.springbootexample.persistence.free_board;

import com.sjkim.springbootexample.domain.FreeBoard;
import com.sjkim.springbootexample.domain.FreeBoardReply;
import com.sjkim.springbootexample.persistence.FreeBoardReplyRepository;
import com.sjkim.springbootexample.persistence.FreeBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FreeBoardRepositoryTest {

    @Autowired
    private FreeBoardRepository freeBoardRepository;
    @Autowired
    private FreeBoardReplyRepository freeBoardReplyRepository;

    @Test
    void addFreeBoard() {
        for (int i = 1; i <= 10; i++) {
            FreeBoard freeBoard = FreeBoard.builder()
                    .title("TITLE_" + i)
                    .content("CONTENT_" + i)
                    .writer("WRITER_" + i)
                    .build();
            FreeBoardReply reply = FreeBoardReply.builder()
                    .reply("REPLY_" + i)
                    .replyer("REPLYER_" + i)
                    .freeBoard(freeBoard).build();
            freeBoardReplyRepository.save(reply);
        }
    }
}