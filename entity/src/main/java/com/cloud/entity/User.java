package com.cloud.entity;

import com.alibaba.fastjson.JSON;
import com.cloud.common.BaseEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.PostMapping;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author WXY
 * @ClassName User
 * @Description T0D0
 * @Date 2019/7/19 15:52
 * @Version 1.0
 **/
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements Serializable {


    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String question;

    @Column
    private String answer;

    @Column
    private Integer role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Cacheable(value = "thisredis",key="'users_'+#id")
    public String getUser(int id) {
        System.out.println( "Method executed.." );
        if (id == 1) {
            return "User 1";
        } else {
            return "User 2";
        }
    }

    @CacheEvict(value="thisredis", key="'users_'+#id",condition="#id!=1")
    @PostMapping("/delUser")
    public void delUser(Integer id) {
        // 删除user
    }



    @Override
    public String toString() {
        return JSON.toJSONString( this );
    }
}