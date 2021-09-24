package com.noriteo.delinori.qna.mapper;

import com.noriteo.delinori.common.config.RootConfig;
import com.noriteo.delinori.qna.config.QnaRootConfig;
import com.noriteo.delinori.qna.domain.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.IntStream;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {QnaRootConfig.class, RootConfig.class})
public class ReplyMapperTests {

    @Autowired(required = false)
    ReplyMapper replyMapper;

    @Test
    public void insertDumies(){
        long[] arr = {131L, 130L, 129L, 128L, 127L};

        IntStream.rangeClosed(1,100).forEach(num ->{
            long qno = arr[((int)(Math.random() * 1000)) % 5];

            Reply reply = Reply.builder()
                    .qno(qno)
                    .reply("댓글......." + num)
                    .replyer("user" + (num % 10))
                    .build();

            replyMapper.insert(reply);
        });
    }

    @Test
    public void testList(){
        Long qno = 131L;

        replyMapper.getListWithQna(qno).forEach(reply -> log.info(reply));
    }
}
