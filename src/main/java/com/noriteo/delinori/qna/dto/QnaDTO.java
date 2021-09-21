package com.noriteo.delinori.qna.dto;

import com.noriteo.delinori.qna.domain.Qna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnaDTO {

    private Long qno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public Qna getDomain() {
        Qna qna = Qna.builder()
                .qno(qno)
                .title(title)
                .content(content)
                .writer(writer)
                .regDate(regDate)
                .modDate(modDate)
                .build();

        return qna;
    }
}
