package com.cloud.common;

import com.cloud.exception.ErrorInfo;
import com.cloud.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WXY
 * @ClassName ExceptionHandler
 * @Description 统一的异常处理类
 * @Date 2019/8/4 20:39
 * @Version 1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

//    @ExceptionHandler(value = MyException.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req,MyException e) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ErrorInfo<String> defaultErrorHandler(HttpServletRequest req,Exception e) throws Exception {
//        ErrorInfo<String> r = new ErrorInfo<>();
//        r.setMessage(e.getMessage());
//        r.setCode(ErrorInfo.ERROR);
//        r.setData("Some Data");
//        r.setUrl(req.getRequestURL().toString());
//        return r;
//    }
}
