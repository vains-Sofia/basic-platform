package com.basic.configuration.jackson;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

import java.math.BigInteger;

/**
 * 修改响应数据中 长整型 的序列化方式，当数字超过 (2^53 - 1) 时会转为字符串。
 * 主要用于解决前端接收大数字时的精度问题。
 *
 * @author vains
 */
public class BigIntToStringSerializer extends StdSerializer<Number> {

    /**
     * 单例
     */
    public static final BigIntToStringSerializer INSTANCE = new BigIntToStringSerializer();

    /**
     * Js 最大安全数字，超出后会失去精度，（2^53 - 1）
     */
    private static final long JS_MAX_SAFE = (1L << 53) - 1;

    protected BigIntToStringSerializer() {
        super(Number.class);
    }

    @Override
    public void serialize(Number value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        if (value == null) {
            gen.writeNull();
            return;
        }

        // 兼容大整数：BigInteger/Long/Integer
        long longVal;
        if (value instanceof BigInteger big) {
            longVal = big.longValueExact();
        } else {
            longVal = value.longValue();
        }

        // 核心判断：超出JS安全范围 → 写字符串
        if (Math.abs(longVal) > JS_MAX_SAFE) {
            gen.writeString(value.toString());
        } else {
            // 没超出 → 原生数字输出，保持格式
            if (value instanceof Integer || value instanceof Byte || value instanceof Short) {
                gen.writeNumber(value.intValue());
            } else if (value instanceof Long) {
                gen.writeNumber(longVal);
            } else if (value instanceof BigInteger big) {
                gen.writeNumber(big);
            } else {
                gen.writeNumber(value.doubleValue());
            }
        }
    }
}
