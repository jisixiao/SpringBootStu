package com.jsz.proscenium.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jushangzhi@novacloud.com
 * @create 2018-05-19 17:09
 * @description
 **/
@Controller
public class TestConrtoller {

    private Logger logger = LoggerFactory.getLogger(TestConrtoller.class);

    @RequestMapping("/view")
    public String view() {
        logger.info("测试Jsp");
        return "/index";
    }

}
