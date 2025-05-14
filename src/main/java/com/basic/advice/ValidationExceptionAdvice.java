package com.basic.advice;

import com.basic.domain.Result;
import com.basic.util.ValidationExceptionResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;

/**
 * 参数校验异常处理
 *
 * @author vains
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionAdvice {

    /**
     * 处理Json请求参数异常
     *
     * @param e 具体地校验异常
     * @return 返回处理后的异常信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<String> methodArgumentNotValidException(HttpServletRequest request,
                                                          MethodArgumentNotValidException e) {
        String errors = ValidationExceptionResolver.resolveFiledErrors(e.getBindingResult().getFieldErrors());
        log.warn("请求[{}]异常，Bean入参校验失败：{}", request.getRequestURI(), errors);
        return Result.error(HttpStatus.BAD_REQUEST.value(), errors);
    }

    /**
     * 处理Form请求参数异常
     *
     * @param e 具体地校验异常
     * @return 返回处理后的异常信息
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> bindException(BindException e,
                                        HttpServletRequest request) {
        String errors = ValidationExceptionResolver.resolveFiledErrors(e.getFieldErrors());
        log.warn("请求[{}]异常，Form参数校验失败：{}", request.getRequestURI(), errors);
        return Result.error(HttpStatus.BAD_REQUEST.value(), errors);
    }

    /**
     * 验证异常处理 - @Validated加在controller类上，且在参数列表中直接指定constraints时触发
     *
     * @param e 具体地校验异常
     * @return 返回处理后的异常信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> methodArgumentNotValidException(HttpServletRequest request,
                                                          ConstraintViolationException e) {
        String errors = ValidationExceptionResolver.resolveConstraintViolations(e);

        log.warn("请求[{}]异常，接口非Bean参数校验失败：{}", request.getRequestURI(), errors);

        return Result.error(HttpStatus.BAD_REQUEST.value(), errors);
    }

    /**
     * 验证异常处理 - 在控制器中对参数进行校验
     * e.g. 直接在接口参数前加 JSR 303相关的注解 @Null、@NotBlank
     *
     * @param e 具体地校验异常
     * @return 返回处理后的异常信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public Result<String> exception(HttpServletRequest request,
                                    HandlerMethodValidationException e) {
        List<? extends MessageSourceResolvable> allErrors = e.getAllErrors();
        String errors = ValidationExceptionResolver.resolveMessageErrors(allErrors);

        log.warn("请求[{}]异常，参数校验失败：{}", request.getRequestURI(), errors);
        return Result.error(HttpStatus.BAD_REQUEST.value(), errors);
    }

}
