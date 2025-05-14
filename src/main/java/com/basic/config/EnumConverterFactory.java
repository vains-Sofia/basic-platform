package com.basic.config;

import com.basic.enums.BasicEnum;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Function;

/**
 * 处理除 {@link org.springframework.web.bind.annotation.RequestBody } 注解标注之外是枚举的入参
 *
 * @param <V> 枚举值的类型
 * @param <E> 枚举的类型
 * @author vains
 */
@Component
public class EnumConverterFactory<V extends Serializable, E extends Enum<E>> implements ConverterFactory<String, BasicEnum<V, E>> {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends BasicEnum<V, E>> Converter<String, T> getConverter(Class<T> targetType) {
        // 获取父接口
        Type baseInterface = targetType.getGenericInterfaces()[0];
        if (baseInterface instanceof ParameterizedType parameterizedType
                && parameterizedType.getActualTypeArguments().length == 2) {
            // 获取具体的枚举类型
            Type targetActualTypeArgument = parameterizedType.getActualTypeArguments()[1];
            Class<?> targetAawArgument = TypeFactory.defaultInstance()
                    .constructType(targetActualTypeArgument).getRawClass();
            // 判断是否实现自通用枚举
            if (BasicEnum.class.isAssignableFrom(targetAawArgument)) {
                // 获取父接口的泛型类型
                Type valueArgument = parameterizedType.getActualTypeArguments()[0];
                // 获取值的class
                Class<V> valueRaw = (Class<V>) TypeFactory.defaultInstance()
                        .constructType(valueArgument).getRawClass();

                String valueOfMethod = "valueOf";
                // 转换入参的类型
                Method valueOf = ReflectionUtils.findMethod(valueRaw, valueOfMethod, String.class);
                if (valueOf != null) {
                    ReflectionUtils.makeAccessible(valueOf);
                }
                // 将String类型的值转为枚举值对应的类型
                Function<String, V> castValue =
                        // 获取不到转换方法时直接返回null
                        source -> {
                            if (valueRaw.isInstance(source)) {
                                // String类型直接强转
                                return valueRaw.cast(source);
                            }
                            // 其它包装类型使用valueOf转换
                            return valueOf == null ? null
                                    : (V) ReflectionUtils.invokeMethod(valueOf, valueRaw, source);
                        };
                return source -> BasicEnum.fromValue(castValue.apply(source), targetType);
            }
        }

        return source -> null;
    }

}
