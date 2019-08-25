package com.cloud.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.cloud.entity.Sender;
import com.cloud.common.OperateResult;
import com.cloud.entity.User;
import com.cloud.exception.MyException;
import com.cloud.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private Sender sender;

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

    @Cacheable(value="thisredis", key="'users_'+#id")
    @PostMapping("/findUser")
    public User findUser(Integer id) {
        User user = new User();
        user.setUsername("hlhdidi");
        user.setPassword("123");
        user.setId(id.toString());
        return user;
    }

    @CacheEvict(value="thisredis", key="'users_'+#id",condition="#id!=1")
    @PostMapping("/delUser")
    public void delUser(Integer id) {
        // 删除user
    }


    @PostMapping("/importTest")
    public OperateResult importTest(@RequestParam(name = "file")MultipartFile file){
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows( 0 );
            params.setHeadRows( 1 );

            return OperateResult.operationSuccess( "ok" );
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/rabbit")
    public void send(){
        sender.send();
    }

}