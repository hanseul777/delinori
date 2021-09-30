package com.noriteo.delinori.common.security.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
public class LoginController {

    @GetMapping("/customLogin")
    public void loginCustom(){
        log.info("===========c   customLogin==========");
    }
}
