package com.noriteo.delinori.qna.mapper;

import com.noriteo.delinori.common.dto.PageRequestDTO;
import com.noriteo.delinori.qna.domain.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {

    int insert(Reply reply);

//    List<Reply> getList(Long qno);

    List<Reply> getListWithQna(@Param("qno") Long qno, @Param("pageRequestDTO") PageRequestDTO pageRequestDTO);

    int delete(Long rno);

    int update(Reply reply);

    int getCountReplies(Long qno);
}
