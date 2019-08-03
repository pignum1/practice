package com.cloud.core;

import com.cloud.common.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础的接口类
 * 注解的作用，再被JPA扫描的时候不会创建实例，只会当作其他的父接口使用
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T ,String>,JpaSpecificationExecutor<T> {

}
