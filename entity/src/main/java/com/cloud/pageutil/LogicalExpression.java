package com.cloud.pageutil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WXY
 * @ClassName LogicalExpression
 * @Description T0D0
 * @Date 2019/7/24 23:54
 * @Version 1.0
 **/
public class LogicalExpression implements Criterion {
    private Criterion[] criterion;  // 逻辑表达式中包含的表达式
    private Operator operator;      //计算符

    public LogicalExpression(Criterion[] criterions, Operator operator) {
        this.criterion = criterions;
        this.operator = operator;
    }

    public Predicate toPredicate(Root<?> root,CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for(int i=0;i<this.criterion.length;i++){
            predicates.add(this.criterion[i].toPredicate(root, query, builder));
        }
        switch (operator) {
            case or:
                return builder.or(predicates.toArray(new Predicate[predicates.size()]));
            case and:
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));

            default:
                return null;
        }
    }

}