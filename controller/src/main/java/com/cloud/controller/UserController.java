package com.cloud.controller;

import com.cloud.common.OperateResult;
import com.cloud.entity.User;
import com.cloud.exception.MyException;
import com.cloud.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WXY
 * @ClassName UserController
 * @Description T0D0
 * @Date 2019/7/21 11:42
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired(required = true)
    private UserService userService;

    //TODO REMOVE
    @RequestMapping("/test")
    public OperateResult test(){
      return userService.test();
    }

    //TODO REMOVE
    @RequestMapping("/test1")
    public OperateResult test1(HttpServletRequest request){
        return userService.test1( request );
    }

    @RequestMapping("register")
    @ApiOperation( value = "用户注册申请",notes = "用户注册申请")
    public OperateResult register(User user){
        return OperateResult.operationSuccess( "保存测试接口" );
    }

    @RequestMapping("/json")
    public String  json() throws MyException {
        throw new MyException("发生错误2");
    }
}