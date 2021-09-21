package com.noriteo.delinori.qna.mapper;

import com.noriteo.delinori.common.dto.PageRequestDTO;
import com.noriteo.delinori.qna.domain.Qna;

import java.util.List;

public interface QnaMapper {
    void insert(Qna qna);

    List<Qna> getList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

    Qna select (Long qno);

    int delete(Long qno);

}
