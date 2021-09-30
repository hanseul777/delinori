package com.noriteo.delinori.qna.service;

import com.noriteo.delinori.common.dto.PageRequestDTO;
import com.noriteo.delinori.common.dto.ReplyResponseDTO;
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
        return replyMapper.getListWithQna(qno, PageRequestDTO.builder().build()).stream()
                .map(reply -> entityToDTO(reply))
                .collect(Collectors.toList());
    }

    @Override
    public ReplyResponseDTO getRepliesPage(PageRequestDTO pageRequestDTO, Long qno) {

        return new ReplyResponseDTO(
                replyMapper.getCountReplies(qno),
                replyMapper.getListWithQna(qno,pageRequestDTO)
        );
    }

    @Override
    public int remove(Long rno) {
        return replyMapper.delete(rno);
    }

    @Override
    public int modify(ReplyDTO replyDTO) {
        return replyMapper.update(dtoToEntity(replyDTO));
    }
}
