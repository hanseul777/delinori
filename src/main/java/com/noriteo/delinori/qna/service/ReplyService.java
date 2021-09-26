package com.noriteo.delinori.qna.service;

import com.noriteo.delinori.qna.domain.Reply;
import com.noriteo.delinori.qna.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {

    int add(ReplyDTO replyDTO);

    List<ReplyDTO> getRepliesWithQno(Long qno);

    int remove(Long rno);

    int modify(ReplyDTO replyDTO);

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
