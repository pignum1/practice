package com.cloud.pageutil;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WXY
 * @ClassName Criteria
 * @Description 条件容器
 * @Date 2019/7/24 23:43
 * @Version 1.0
 **/
public class Criteria<T> implements Specification<T> {
    private List<Criterion> criterions = new ArrayList<Criterion>();


    public Predicate toPredicate(Root<T> root,CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        if (!criterions.isEmpty()) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            for(Criterion c : criterions){
                predicates.add(c.toPredicate(root, query,builder));
            }
            // 将所有条件用 and 联合起来
            if (predicates.size() > 0) {
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return builder.conjunction();
    }


    /**
     * 增加简单条件表达式
     * @Methods Name add
     * @param
     */
    public Criteria<T> add(Criterion criterion){

        if(criterion!=null){
            criterions.add(criterion);
        }

        return this;
    }
}