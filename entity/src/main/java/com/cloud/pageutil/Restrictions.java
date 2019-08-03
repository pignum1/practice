package com.cloud.pageutil;

import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @author WXY
 * @ClassName Restrictions
 * @Description T0D0
 * @Date 2019/7/24 23:55
 * @Version 1.0
 **/
public class Restrictions {

    /**
     * 等于
     *
     * @param fieldName
     * @param value
     * @return
     */
    public static SimpleExpression eq(String fieldName,Object value) {
//        if (StringUtils.isEmpty( value )) return null;
        return new SimpleExpression( fieldName,value,Criterion.Operator.eq );
    }

    /**
     * 不等于
     *
     * @param fieldName
     * @param value
     * @return
     */
    public static SimpleExpression ne(String fieldName,Object value) {
        if (StringUtils.isEmpty( value )) return null;
        return new SimpleExpression( fieldName,value,Criterion.Operator.ne );
    }

    /**
     * 模糊匹配
     *
     * @param fieldName
     * @param value
     * @param
     * @return
     */
    public static SimpleExpression like(String fieldName,String value) {
        if (StringUtils.isEmpty( value )) return null;
        return new SimpleExpression( fieldName,value,Criterion.Operator.like );
    }


    /**
     * 大于
     *
     * @param fieldName
     * @param value
     * @return
     */
    public static SimpleExpression gt(String fieldName,Object value) {
        if (StringUtils.isEmpty( value )) return null;
        return new SimpleExpression( fieldName,value,Criterion.Operator.gt );
    }

    /**
     * 小于
     *
     * @param fieldName
     * @param value
     * @return
     */
    public static SimpleExpression lt(String fieldName,Object value) {
        if (StringUtils.isEmpty( value )) return null;
        return new SimpleExpression( fieldName,value,Criterion.Operator.lt );
    }

    /**
     * 大于等于
     *
     * @param fieldName
     * @param value
     * @return
     */
    public static SimpleExpression lte(String fieldName,Object value) {
        if (StringUtils.isEmpty( value )) return null;
        return new SimpleExpression( fieldName,value,Criterion.Operator.lte );
    }

    /**
     * 小于等于
     *
     * @param fieldName
     * @param value
     * @param
     * @return
     */
    public static SimpleExpression gte(String fieldName,Object value) {
        if (StringUtils.isEmpty( value )) return null;
        return new SimpleExpression( fieldName,value,Criterion.Operator.gte );
    }


    /**
     * 并且
     *
     * @param criterions
     * @return
     */
    public static LogicalExpression and(Criterion... criterions) {
        return new LogicalExpression( criterions,Criterion.Operator.and );
    }

    /**
     * 或者
     *
     * @param criterions
     * @return
     */
    public static LogicalExpression or(Criterion... criterions) {
        return new LogicalExpression( criterions,Criterion.Operator.or );
    }

    /**
     * 包含于
     *
     * @param fieldName
     * @param value
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression in(String fieldName,Collection value,boolean ignoreNull) {
        if (ignoreNull && (value == null || value.isEmpty())) {
            return null;
        }
        SimpleExpression[] ses = new SimpleExpression[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new SimpleExpression( fieldName,obj,Criterion.Operator.eq );
            i++;
        }
        return new LogicalExpression( ses,Criterion.Operator.or );
    }

    /**
     * 不包含于
     *
     * @param fieldName
     * @param value
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression notin(String fieldName,Collection value,boolean ignoreNull) {
        if (ignoreNull && (value == null || value.isEmpty())) {
            return null;
        }
        SimpleExpression[] ses = new SimpleExpression[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new SimpleExpression( fieldName,obj,Criterion.Operator.ne );
            i++;
        }
        return new LogicalExpression( ses,Criterion.Operator.and );
    }

    /**
     * between
     *
     * @param fieldName
     * @param object1
     * @param object2
     * @return
     */
    public static LogicalExpression between(String fieldName,Object object1,Object object2) {
        if (object1 == null || object2 == null) {
            return null;
        }
        SimpleExpression[] ses = new SimpleExpression[2];
        ses[0] = new SimpleExpression( fieldName,object1,Criterion.Operator.gte );
        ses[1] = new SimpleExpression( fieldName,object2,Criterion.Operator.lte );
        return new LogicalExpression( ses,Criterion.Operator.and );
    }

    /**
     * 为空
     * @param fieldName
     * @param value
     * @return
     */
    public static SimpleExpression isNull(String fieldName,Object value){
        return new SimpleExpression( fieldName,value,Criterion.Operator.isNuLL );
    }

    /**
     * 不为空
     * @param fieldName
     * @param value
     * @return
     */
    public static SimpleExpression notNull(String fieldName,Object value){
        return new SimpleExpression( fieldName,value,Criterion.Operator.notNull );
    }

}