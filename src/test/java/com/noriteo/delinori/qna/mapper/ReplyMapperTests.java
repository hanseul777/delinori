package com.noriteo.delinori.qna.mapper;

import com.noriteo.delinori.common.config.RootConfig;
import com.noriteo.delinori.common.dto.PageRequestDTO;
import com.noriteo.delinori.qna.config.QnaRootConfig;
import com.noriteo.delinori.qna.domain.Qna;
import com.noriteo.delinori.qna.domain.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.IntStream;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {QnaRootConfig.class, RootConfig.class})
public class ReplyMapperTests {

    @Autowired(required = false)
    ReplyMapper replyMapper;

    @Test
    public void insertDumies(){
        long[] arr = {141L, 140L, 139L, 138L, 137L};

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
        Long qno = 141L;

        replyMapper.getList(qno).forEach(reply -> log.info(reply));
    }

    @Test
    public void testUpdate(){
        Reply reply = Reply.builder()
                .rno(1L)
                .qno(131L)
                .replyer("test user")
                .reply("댓글수정 mapper test")
                .build();

        log.info(replyMapper.update(reply));
    }

    @Test
    public void testDelete(){
        Long rno = 3L;

        log.info(replyMapper.delete(rno));
    }

    @Test
    public void testReplyPage(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10).build();

        List<Reply> replies = replyMapper.getListWithQna(137L,pageRequestDTO);

        replies.forEach(reply -> log.info(reply));

    }
}
