package com.noriteo.delinori.qna.controller;

import com.noriteo.delinori.qna.dto.ReplyDTO;
import com.noriteo.delinori.qna.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("")
    public int add(@RequestBody ReplyDTO replyDTO){

        log.info("==========c reply add===========");
        log.info(replyDTO);

        return replyService.add(replyDTO);
    }

    @GetMapping("/list/{qno}")
    public List<ReplyDTO> getQnareplies(@PathVariable(name = "qno") Long qno){
        return replyService.getRepliesWithQno(qno);
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
