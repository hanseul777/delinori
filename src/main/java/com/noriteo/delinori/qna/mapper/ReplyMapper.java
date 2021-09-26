package com.noriteo.delinori.qna.mapper;

import com.noriteo.delinori.qna.domain.Reply;

import java.util.List;

public interface ReplyMapper {

    int insert(Reply reply);

    List<Reply> getListWithQna(Long qno);

    int delete(Long rno);

    int update(Reply reply);
}
