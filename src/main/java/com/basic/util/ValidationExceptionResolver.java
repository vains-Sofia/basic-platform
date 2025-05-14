package com.basic.util;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 参数校验异常处理类
 *
 * @author vains
 */
public class ValidationExceptionResolver {

    /**
     * 符号常量
     */
    private static final String DOT = ".";
    private static final String SEPARATOR_COMMA = ", ";
    private static final String SEPARATOR_COLON = ": ";

    /**
     * 转换FieldError列表为错误提示信息
     *
     * @param fieldErrors 字段异常信息
     * @return 拼接后的异常信息
     */
    public static String resolveFiledErrors(List<FieldError> fieldErrors) {
        // 将字段异常类转为字符串
        Function<FieldError, Stream<String>> fieldErrorConverter =
                fieldError -> Stream.of(fieldError.isBindingFailure() ? "" : fieldError.getField(),
                        fieldError.isBindingFailure() ? "" : " ",
                        fieldError.getDefaultMessage(), SEPARATOR_COMMA);
        // 转为字符串
        return Optional.ofNullable(fieldErrors)
                .map(fieldErrorsInner -> fieldErrorsInner.stream()
                        .flatMap(fieldErrorConverter)
                        .collect(Collectors.joining()))
                .map(msg -> msg.substring(0, msg.length() - SEPARATOR_COMMA.length()))
                .orElse(null);
    }

    /**
     * 转换ConstraintViolationException异常为错误提示信息
     *
     * @param constraintViolationException 参数中基础变量校验的异常信息
     * @return 返回截取后的异常
     */
    public static String resolveConstraintViolations(ConstraintViolationException constraintViolationException) {
        return Optional.ofNullable(constraintViolationException.getConstraintViolations())
                .map(constraintViolations -> constraintViolations.stream()
                        .flatMap(constraintViolation -> {
                            String path = constraintViolation.getPropertyPath().toString();
                            path = path.substring(path.lastIndexOf(DOT) + 1);
                            String errMsg = constraintViolation.getMessage();
                            return Stream.of(path, SEPARATOR_COLON, errMsg, SEPARATOR_COMMA);
                        }).collect(Collectors.joining())
                ).map(msg -> msg.substring(0, msg.length() - SEPARATOR_COMMA.length()))
                .orElse(null);

    }

    /**
     * 处理接口入参校验异常信息
     *
     * @param allErrors 接口校验失败问题
     * @return 返回截取后的异常
     */
    public static String resolveMessageErrors(List<? extends MessageSourceResolvable> allErrors) {
        return Optional.ofNullable(allErrors)
                .map(allErrorsInner -> allErrorsInner.stream()
                        .flatMap(allError -> Stream.of(allError.getDefaultMessage(), SEPARATOR_COMMA))
                        .collect(Collectors.joining())
                )
                .map(msg -> msg.substring(0, msg.length() - SEPARATOR_COMMA.length()))
                .orElse(null);
    }

}
