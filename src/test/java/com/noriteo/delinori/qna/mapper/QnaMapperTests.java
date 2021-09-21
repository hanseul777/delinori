package com.noriteo.delinori.qna.mapper;

import com.noriteo.delinori.common.config.RootConfig;
import com.noriteo.delinori.common.dto.PageRequestDTO;
import com.noriteo.delinori.qna.config.QnaRootConfig;
import com.noriteo.delinori.qna.domain.Qna;
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
public class QnaMapperTests {
    @Autowired
    QnaMapper qnaMapper;

    @Test
    public void testDummies() {

        IntStream.rangeClosed(1,30).forEach(i -> {
            Qna qna = Qna.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer("user" + (i % 10))
                    .build();

            qnaMapper.insert(qna);
        });

    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        log.info(pageRequestDTO);

        qnaMapper.getList(pageRequestDTO).forEach(qna -> {log.info(qna);});
    }

    @Test
    public void testSelect(){
        Qna qna = qnaMapper.select(200L);
        log.info("====================================================");
        log.info(qna);
    }

    @Test
    public void testDelete(){
        Long qno = 200L;

        log.info("================delete test================");
        log.info(qnaMapper.delete(qno));
    }
}
