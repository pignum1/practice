package com.cloud.service;

import com.cloud.common.OperateResult;
import com.cloud.dao.UserDao;
import com.cloud.entity.User;
import com.cloud.pageutil.Criteria;
import com.cloud.pageutil.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WXY
 * @ClassName UserSerivice
 * @Description T0D0
 * @Date 2019/7/21 11:42
 * @Version 1.0
 **/
@Service
public class UserService  {
    @Autowired
    private UserDao userDao;

    public OperateResult  test(){
        try {
            User user = userDao.getOne( "402881826c12fe46016c12fe6bad0000" );
            user.setId( null );
            User user1 = new User();
            BeanUtils.copyProperties( user,user1 );
            user1.setId( null );
            userDao.save( user1 );
            return OperateResult.operationSuccess( "" );
        }catch (Exception e){
            e.printStackTrace();
            return OperateResult.operationFailure( e.getMessage() );
        }
    }

    public OperateResult  test1(HttpServletRequest request){
        Pageable pageable =   PageRequest.of(Integer.parseInt( request.getParameter( "page" ) )-1,Integer.parseInt( request.getParameter( "size" ) ));
        List<String> filter = new ArrayList<>();
        filter.add( "materialCode" );
        filter.add( "materialDesc" );
        Criteria<User> criteria = null;
        try {
            criteria = PageUtils.createCriterion( request,filter );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Page<User> page = userDao.findAll(criteria, pageable );
        return OperateResult.operationSuccess( "321" );
    }
}