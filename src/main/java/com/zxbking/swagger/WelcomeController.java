package com.zxbking.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Generated;

/**
 * Created by zhangxibin on 2017/8/28.
 */
@Api(tags = {"类型1"},description = "测试api内容",position = 30)
@RestController
@RequestMapping()
public class WelcomeController {
    @GetMapping("/open/welcome/save1")
    @ApiOperation(
            value = "Find purchase order by ID",
            notes = "For valid response try integer IDs with value <= 5 or > 10. Other values will generated exceptions",
            tags = {"Pet Store"})
    public String save1(){
        return "1000";
    }
    @GetMapping("/open/welcome/save2")
    @ApiOperation(value = "保存数据到redis")
    public String save2(){
        return "1000";
    }
    @GetMapping("/rest/welcome/save1")
    @ApiOperation(value = "保存数据到redis")
    public String save3(){
        return "1000";
    }
    @GetMapping("/rest/welcome/save2")
    @ApiOperation(value = "保存数据到redis")
    public String save4(){
        return "1000";
    }
}
