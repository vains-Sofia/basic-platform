package com.basic.config.doc;

import com.basic.enums.BasicEnum;
import com.fasterxml.jackson.databind.type.SimpleType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.customizers.PropertyCustomizer;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 枚举属性自定义配置
 *
 * @author vains
 */
public class ApiEnumPropertyCustomizer implements PropertyCustomizer, BasicEnumCustomizer {

    @Override
    public Schema<?> customize(Schema property, AnnotatedType type) {
        // 检查实例并转换
        if (type.getType() instanceof SimpleType fieldType) {
            // 获取字段class
            Class<?> fieldClazz = fieldType.getRawClass();
            // 是否是枚举
            if (BasicEnum.class.isAssignableFrom(fieldClazz)) {
                // 获取父接口
                if (fieldClazz.getGenericInterfaces()[0] instanceof ParameterizedType parameterizedType) {

                    // 通过父接口获取泛型中枚举值的class类型
                    Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
                    Schema<Object> schema = getSchemaByType(actualTypeArgument, property);

                    // 重新设置字段的注释和默认值
                    schema.setEnum(this.getValues(fieldClazz));

                    // 获取字段注释
                    String description = this.getDescription(fieldClazz);

                    // 重置字段注释和标题为从枚举中提取的
                    if (ObjectUtils.isEmpty(property.getTitle())) {
                        schema.setTitle(description);
                    }
                    if (ObjectUtils.isEmpty(property.getDescription())) {
                        schema.setDescription(description);
                    } else {
                        schema.setDescription(property.getDescription() + " (" + description + ")");
                    }
                    return schema;
                }
            }
        }
        return property;
    }

}

