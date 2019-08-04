package com.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author WXY
 * @ClassName ThymeleafController
 * @Description Thymeleaf模板引擎测试
 * @Date 2019/8/4 15:45
 * @Version 1.0
 **/
@Controller
public class ThymeleafController {

    @RequestMapping("/thymeleaf")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "https://pignum1.github.io/");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }
}