package com.cloud.pageutil;

import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

/**
 * @author WXY
 * @ClassName SimpleExpression
 * @Description T0D0
 * @Date 2019/7/24 23:55
 * @Version 1.0
 **/
public class SimpleExpression implements Criterion{

    private String fieldName;       //属性名
    private Object value;           //对应值
    private Operator operator;      //计算符

    protected SimpleExpression(String fieldName, Object value, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    public String getFieldName() {
        return fieldName;
    }
    public Object getValue() {
        return value;
    }
    public Operator getOperator() {
        return operator;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Predicate toPredicate(Root<?> root,CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        Path expression = null;
        if(fieldName.contains(".")){
            String[] names = StringUtils.split(fieldName, ".");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        }else{
            expression = root.get(fieldName);
        }
        switch (operator) {
            case eq:
                return builder.equal(expression, value);
            case ne:
                return builder.notEqual(expression, value);
            case like:
                return builder.like((Expression<String>) expression, "%" + value + "%");
            case lt:
                return builder.lessThan(expression, (Comparable) value);
            case gt:
                return builder.greaterThan(expression, (Comparable) value);
            case lte:
                return builder.lessThanOrEqualTo(expression, (Comparable) value);
            case gte:
                return builder.greaterThanOrEqualTo(expression, (Comparable) value);
            case isNuLL:
                return builder.isNull( expression );
            case notNull:
                return builder.isNotNull( expression );
            default:
                return null;
        }
    }


}