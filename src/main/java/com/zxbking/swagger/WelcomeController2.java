package com.zxbking.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangxibin on 2017/8/28.
 */
@Api(tags = {"类型1"},description = "测2api内容",position = 50)
@RestController
@RequestMapping()
public class WelcomeController2 {
    @GetMapping("/open/welcome1/save1")
    @ApiOperation(value = "保存数据到redis")
    public String save1(){
        return "1000";
    }
    @GetMapping("/open/welcome1/save2")
    @ApiOperation(value = "保存数据到redis")
    public String save2(){
        return "1000";
    }
    @GetMapping("/rest/welcome1/save1")
    @ApiOperation(value = "保存数据到redis")
    public String save3(){
        return "1000";
    }
    @GetMapping("/rest/welcome1/save2")
    @ApiOperation(value = "保存数据到redis")
    public String save4(){
        return "1000";
    }
}
