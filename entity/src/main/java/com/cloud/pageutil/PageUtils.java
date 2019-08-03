package com.cloud.pageutil;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author WXY
 * @ClassName PageUtils
 * @Description T0D0
 * @Date 2019/7/24 23:56
 * @Version 1.0
 **/
public class PageUtils {
    /**
     * 创建查询的条件容器
     * @param request
     * @param <T>
     * @return
     */
    public static<T> Criteria<T> createCriterion(HttpServletRequest request,List<String> filter) throws ParseException {
        Criteria<T> criteria = new Criteria<>();
        //判断是否有快速查询条件
        if(!StringUtils.isEmpty(request.getParameter( "Quick_Value" ))){
            String quickValue = request.getParameter( "Quick_Value" );
            List<Criterion> criterionList = new ArrayList<>(  );
            if(!CollectionUtils.isEmpty( filter )){
                filter.stream().forEach( filedName -> {
                    criterionList.add( Restrictions.like( filedName,quickValue ) );
                } );
                Criterion[] criteriaArray = new Criterion[criterionList.size()];
                criterionList.toArray(criteriaArray);
                criteria.add( Restrictions.or( criteriaArray ) );
            }
        }
        //获取查询条件（and、or）
        Map<String, Object> queryMap = WebUtils.getParametersStartingWith(request, "search_");
        //or条件的查询 所有or一起查询，目前没想到灵活添加or的方法
        List<Criterion> criterionList = new ArrayList<>(  );
        Iterator<Map.Entry<String, Object>> entries = queryMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            String key = entry.getKey();
            String value = (String) entry.getValue();
            String[] array = key.split("_");
            String filedName = array[0];
            String operator = array[1];
            //根据operator执行不同的比较
            switch (operator){
                case "eq":
                    criteria.add(Restrictions.eq(filedName,value));
                    break;
                case "lk":
                    criteria.add( Restrictions.like( filedName,value ) );
                    break;
                case "ne":
                    criteria.add( Restrictions.ne( filedName,value ) );
                    break;
                case "lt":
                    criteria.add( Restrictions.lt( filedName,value ) );
                    break;
                case "gt":
                    criteria.add( Restrictions.gt( filedName,value ) );
                    break;
                case "lte":
                    criteria.add( Restrictions.lte( filedName,value ) );
                    break;
                case "gte":
                    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                    criteria.add( Restrictions.gte( filedName,sdf.parse( value ) ) );
                    break;
                case "null":
                    criteria.add( Restrictions.isNull( filedName,value ) );
                    break;
                case "notNull":
                    criteria.add( Restrictions.notNull( filedName,value ) );
                    break;
                case "or":
                    if(array.length==3){
                        String extraOperator= array[2];
                        switch (extraOperator){
                            case "eq":
                                criterionList.add(Restrictions.eq( filedName,value )  );
                                break;
                            case "null":
                                criterionList.add(Restrictions.isNull( filedName,value )  );
                                break;
                            case "notNull":
                                criterionList.add( Restrictions.notNull( filedName,value ) );
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        if(!CollectionUtils.isEmpty( criterionList )) {
            Criterion[] criteriaArray = new Criterion[criterionList.size()];
            criterionList.toArray( criteriaArray );
            criteria.add( Restrictions.or( criteriaArray ) );
        }
        return criteria;
    }

    public static<T> void quickValueParams(HttpServletRequest request,Criteria<T> criteria){
        String quickValue = request.getParameter( "quickValue" );
        List<String> filter = getFilterList(  );
        if(!CollectionUtils.isEmpty( filter )){
            filter.stream().forEach( filedName -> {
                criteria.add(Restrictions.or( Restrictions.like( filedName,quickValue ) ));
            } );
        }
    }

    public static<T> List<String>  getFilterList(){
        return new ArrayList<>( );
    }
}