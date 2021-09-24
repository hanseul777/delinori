package com.noriteo.delinori.qna.service;

import com.noriteo.delinori.qna.dto.ReplyDTO;
import com.noriteo.delinori.qna.mapper.QnaMapper;
import com.noriteo.delinori.qna.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService{

    private final ReplyMapper replyMapper;
    private final QnaMapper qnaMapper;

    @Override
    public int add(ReplyDTO replyDTO) {
        int count = replyMapper.insert(dtoToEntity(replyDTO));
        qnaMapper.updateReplyCnt(replyDTO.getQno(),1);

        return count;
    }

    @Override
    public List<ReplyDTO> getRepliesWithQno(Long qno) {
        return replyMapper.getListWithQna(qno).stream()
                .map(reply -> entityToDTO(reply))
                .collect(Collectors.toList());
    }
}
