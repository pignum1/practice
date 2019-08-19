package com.cloud.core;

import com.cloud.common.BaseEntity;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


/**
 * @author WXY
 * @ClassName BaseDaoImpl
 * @Description 自定义基类接口实现类
 * @Date 2019/7/22 13:25
 * @Version 1.0
 **/
@Transactional
public class BaseDaoImpl<T extends BaseEntity>  extends SimpleJpaRepository<T,String> implements BaseRepository<T> {

    public BaseDaoImpl(Class<T> entityInformation,EntityManager entityManager) {
        super( entityInformation,entityManager );
    }

    @Override
    public <S extends T> S save(S entity) {
        return super.save( entity );
    }
}