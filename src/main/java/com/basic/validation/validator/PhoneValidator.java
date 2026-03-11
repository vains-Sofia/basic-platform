package com.basic.validation.validator;

import com.basic.validation.annotation.Phone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 手机号校验器
 *
 * @author vains
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    /**
     * 手机号正则表达式
     * ^ 表示字符串的开始
     * 1 表示手机号的第一个数字是 1
     * [3-9] 表示第二个数字必须是 3 到 9 之间的一个数字
     * \\d{9} 表示之后跟着是 9 个数字
     * $ 表示字符串的结束
     */
    public static final String REGEX_PHONE = "^1[3-9]\\d{9}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            // 为空的情况下该校验器不处理
            return true;
        }
        if (value.length() != 11) {
            return false;
        }

        // 正则校验
        return value.matches(REGEX_PHONE);
    }
}
