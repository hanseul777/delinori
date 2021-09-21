package com.noriteo.delinori.qna.domain;

import com.noriteo.delinori.qna.dto.QnaDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Qna {

    private Long qno;
    private String title, content, writer;
    private LocalDateTime regDate, modDate;

    public QnaDTO getDTO() {
        QnaDTO qnaDTO = QnaDTO.builder()
                .qno(qno)
                .title(title)
                .content(content)
                .writer(writer)
                .regDate(regDate)
                .modDate(modDate)
                .build();

        return qnaDTO;
    }

    public void setQno(Long qno) {
        this.qno = qno;
    }
}
