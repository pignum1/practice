package com.cloud.pageutil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Criterion {
    public enum Operator {
        eq, ne, like, gt, lt, gte, lte, and, or,isNuLL,notNull
    }

    public Predicate toPredicate(Root<?> root,CriteriaQuery<?> query,
                                 CriteriaBuilder builder);
}
