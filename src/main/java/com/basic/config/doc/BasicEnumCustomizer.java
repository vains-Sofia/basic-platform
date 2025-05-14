package com.basic.config.doc;

import io.swagger.v3.core.util.PrimitiveType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 基础自定义接口
 *
 * @author vains
 */
public interface BasicEnumCustomizer {

    /**
     * 获取枚举的所有值
     *
     * @param enumClazz 枚举的class
     * @return 枚举的所有值
     */
    default List<Object> getValues(Class<?> enumClazz) {
        return Arrays.stream(enumClazz.getEnumConstants())
                .filter(Objects::nonNull)
                .map(item -> {
                    // 收集values
                    Method getValue = ReflectionUtils.findMethod(item.getClass(), "getValue");
                    if (getValue != null) {
                        ReflectionUtils.makeAccessible(getValue);
                        return ReflectionUtils.invokeMethod(getValue, item);
                    }
                    return null;
                }).filter(Objects::nonNull).toList();
    }

    /**
     * 获取值和描述对应的描述信息，值和描述信息以“:”隔开
     *
     * @param enumClazz 枚举class
     * @return 描述信息
     */
    default String getDescription(Class<?> enumClazz) {
        List<Field> fieldList = Arrays.stream(enumClazz.getDeclaredFields())
                .filter(f -> !Modifier.isStatic(f.getModifiers()))
                // 排序
                .sorted(Comparator.comparing(Field::getName).reversed())
                .toList();
        fieldList.forEach(ReflectionUtils::makeAccessible);
        return Arrays.stream(enumClazz.getEnumConstants())
                .filter(Objects::nonNull)
                .map(item -> fieldList.stream()
                        .map(field -> ReflectionUtils.getField(field, item))
                        .map(String::valueOf)
                        .collect(Collectors.joining(" : ")))
                .collect(Collectors.joining("； "));
    }

    /**
     * 根据枚举值的类型获取对应的 {@link Schema} 类
     *  这么做是因为当SpringDoc获取不到属性的具体类型时会自动生成一个string类型的 {@link Schema} ，
     *  所以需要根据枚举值的类型获取不同的实例，例如 {@link io.swagger.v3.oas.models.media.IntegerSchema}、
     *  {@link io.swagger.v3.oas.models.media.StringSchema}
     *
     * @param type         枚举值的类型
     * @param sourceSchema 从属性中加载的 {@link Schema} 类
     * @return 获取枚举值类型对应的 {@link Schema} 类
     */
    @SuppressWarnings({"unchecked"})
    default Schema<Object> getSchemaByType(Type type, Schema<?> sourceSchema) {
        Schema<Object> schema;
        PrimitiveType item = PrimitiveType.fromType(type);

        if (item == null) {
            schema = new ObjectSchema();
        } else {
            schema = item.createProperty();
        }

        // 获取schema的type和format
        String schemaType = schema.getType();
        String format = schema.getFormat();
        // 复制原schema的其它属性
        BeanUtils.copyProperties(sourceSchema, schema);

        // 使用根据枚举值类型获取到的schema
        return schema.type(schemaType).format(format);
    }

}

