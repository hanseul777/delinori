package com.noriteo.delinori.common.security.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/doAll")
    public void doAll(){log.info("============doAll============");}

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/doDeli")
    public void doDeli(){
        log.info("===========doDeli===========");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/doNori")
    public void doNori(){
        log.info("=========doNori==========");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/doAdmin")
    public void doAdmin(){
        log.info("=========doAdmin========");
    }
}
