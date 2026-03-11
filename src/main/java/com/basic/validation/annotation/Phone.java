package com.basic.validation.annotation;

import com.basic.validation.validator.PhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 验证手机号注解
 *
 * @author vains
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface Phone {

    String message() default "{basic.cloud.phone}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
