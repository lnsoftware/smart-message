package com.mysoft.smart.message.web.advice;

import com.mysoft.smart.message.api.WrapMapper;
import com.mysoft.smart.message.api.Wrapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @auth lnsof
 * @Date 2018/9/13 20:28
 * @Version 1.0
 */
@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Wrapper<?> requestParameterException(MethodArgumentNotValidException ex) {
        StringBuffer sb = new StringBuffer();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            sb.append(fieldError.getDefaultMessage()).append(",");
        }
        return WrapMapper.wrap(HttpStatus.BAD_REQUEST.value(), sb.toString());
    }

}
