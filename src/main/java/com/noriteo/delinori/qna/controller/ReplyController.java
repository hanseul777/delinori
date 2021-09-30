package com.noriteo.delinori.qna.controller;

import com.noriteo.delinori.common.dto.PageRequestDTO;
import com.noriteo.delinori.common.dto.PageResponseDTO;
import com.noriteo.delinori.common.dto.ReplyResponseDTO;
import com.noriteo.delinori.qna.domain.Reply;
import com.noriteo.delinori.qna.dto.QnaDTO;
import com.noriteo.delinori.qna.dto.ReplyDTO;
import com.noriteo.delinori.qna.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public int add(@RequestBody ReplyDTO replyDTO){

        log.info("==========c reply add===========");
        log.info(replyDTO);

        return replyService.add(replyDTO);
    }

//    @ResponseBody
//    @GetMapping("/list/{qno}")
//    public List<ReplyDTO> getQnareplies(@PathVariable(name = "qno") Long qno){
//        return replyService.getReplies(qno);
//    }

    @ResponseBody
    @GetMapping("/list/{qno}/{page}")
    public ReplyResponseDTO getQnaReplies(@PathVariable(name = "qno") Long qno , @PathVariable(name="page") int page){

        log.info("===================c  getQnaReplies================");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(page)
                .size(10).build();

        log.info("=====================================");
        log.info("qno : " + qno);
        log.info("pageRequestDTO : " + pageRequestDTO);

        List<ReplyDTO> replyDTOList = replyService.getRepliesWithQno(qno);
        int replyCount = 200;

        return ReplyResponseDTO.<ReplyDTO>builder().replyCnt(replyCount).list(replyDTOList).build();
    }

    @DeleteMapping("/{rno}")
    public String remove(@PathVariable(name = "rno") Long rno){
        log.info("========c        replyRemove========");
        log.info(rno);

        replyService.remove(rno);

        return "success";
    }

    @PutMapping("/{rno}")
    public String modify(@PathVariable(name="rno") Long rno, @RequestBody ReplyDTO replyDTO){
        log.info("========c        replyModify========" + rno);
        log.info(replyDTO);

        replyService.modify(replyDTO);

        return "sucess";
    }
}
