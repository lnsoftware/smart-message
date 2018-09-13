package com.mysoft.smart.message.web.advice;

import com.mysoft.smart.message.api.WrapMapper;
import com.mysoft.smart.message.api.Wrapper;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
        List<String> errors = new ArrayList<String>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getDefaultMessage());
        }
        return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE, errors);
    }

}
