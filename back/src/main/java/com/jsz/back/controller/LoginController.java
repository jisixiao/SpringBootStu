package com.jsz.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登入controller
 *
 * @author jushangzhi@novacloud.com
 * @create 2018-05-24 16:25
 * @description
 **/
@RestController
@RequestMapping("/login")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public void login (String userName ,String userPwd){
        logger.info(userName+" : "+userPwd);
    }

}
