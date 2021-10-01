package com.noriteo.delinori.qna.service;

import com.noriteo.delinori.common.dto.PageRequestDTO;
import com.noriteo.delinori.common.dto.PageResponseDTO;
import com.noriteo.delinori.common.dto.ReplyResponseDTO;
import com.noriteo.delinori.qna.domain.Reply;
import com.noriteo.delinori.qna.dto.QnaDTO;
import com.noriteo.delinori.qna.dto.ReplyDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReplyService {

    int add(ReplyDTO replyDTO);

    List<ReplyDTO> getRepliesWithQno(Long qno);

    int remove(Long rno);

    int modify(ReplyDTO replyDTO);

    ReplyResponseDTO getRepliesPage(PageRequestDTO pageRequestDTO, Long qno);

    default Reply dtoToEntity(ReplyDTO dto){

        Reply reply = Reply.builder()
                .rno(dto.getRno())
                .qno(dto.getQno())
                .reply(dto.getReply())
                .replyer(dto.getReplyer())
                .replyDate(dto.getReplyDate())
                .modDate(dto.getModDate())
                .build();

        return reply;
    }

    default ReplyDTO entityToDTO(Reply reply){
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(reply.getRno())
                .qno(reply.getQno())
                .reply(reply.getReply())
                .replyer(reply.getReplyer())
                .replyDate(reply.getReplyDate())
                .modDate(reply.getModDate())
                .build();

        return replyDTO;
    }
}
