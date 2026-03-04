package com.basic.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.type.TypeFactory;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/**
 * <p>
 * JSON与对象互转帮助类
 * </p>
 *
 * @author vains
 * @since 2020-11-10
 */
@Slf4j
@UtilityClass
public class JsonUtils {

    private final static JsonMapper MAPPER;

    static {
        JsonMapper.Builder builder = JsonMapper.builderWithJackson2Defaults();
        // 设置 Date类型的序列化及反序列化格式
        builder.defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 日期不序列化为 Timestamps
        builder.disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS);
        builder.disable(DateTimeFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
        MAPPER = builder.build();
    }

    /**
     * json字符串转为对象
     *
     * @param json  json
     * @param clazz T类的class文件
     * @param <T>   泛型, 代表返回参数的类型
     * @return 返回T的实例
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        if (json == null || clazz == null) {
            return null;
        }
        return MAPPER.readValue(json, clazz);
    }

    /**
     * json字符串转为对象
     *
     * @param json json
     * @param type 对象在Jackson中的类型
     * @param <T>  泛型, 代表返回参数的类型
     * @return 返回T的实例
     */
    public static <T> T toObject(String json, TypeReference<T> type) {
        if (json == null || type == null) {
            return null;
        }
        return MAPPER.readValue(json, type);
    }

    /**
     * json字符串转为对象
     *
     * @param json json
     * @param type 对象的类型
     * @param <T>  泛型, 代表返回参数的类型
     * @return 返回T的实例
     */
    public static <T> T toObject(String json, Type type) {
        TypeFactory typeFactory = MAPPER.getTypeFactory();
        JavaType javaType = typeFactory.constructType(type);
        return MAPPER.readValue(json, javaType);
    }

    /**
     * 将流中的数据转为java对象
     *
     * @param inputStream 输入流
     * @param clazz       类的class
     * @param <T>         泛型, 代表返回参数的类型
     * @return 返回对象 如果参数任意一个为 null则返回null
     */
    public static <T> T streamToObject(InputStream inputStream, Class<T> clazz) {
        if (inputStream == null || clazz == null) {
            return null;
        }
        return MAPPER.readValue(inputStream, clazz);
    }

    /**
     * json字符串转为复杂类型List/Map
     *
     * @param json            json
     * @param collectionClazz 集合的class
     * @param elementsClazz   集合中泛型的class
     * @param <T>             泛型, 代表返回参数的类型
     * @return 返回T的实例
     */
    public static <T> T toObject(String json, Class<?> collectionClazz, Class<?>... elementsClazz) {
        if (json == null || collectionClazz == null || elementsClazz == null) {
            return null;
        }
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(collectionClazz, elementsClazz);
        return MAPPER.readValue(json, javaType);
    }

    /**
     * 对象转为json字符串
     *
     * @param o 将要转化的对象
     * @return 返回json字符串
     */
    public static String toJson(Object o) {
        if (o == null) {
            return null;
        }
        return o instanceof String s ? s : MAPPER.writeValueAsString(o);
    }

    /**
     * 将对象转为另一个对象
     * 切记,两个对象结构要一致
     * 多用于Object转为具体的对象
     *
     * @param o               将要转化的对象
     * @param collectionClazz 集合的class
     * @param elementsClazz   集合中泛型的class
     * @param <T>             泛型, 代表返回参数的类型
     * @return 返回T的实例
     */
    public static <T> T objectToObject(Object o, Class<?> collectionClazz, Class<?>... elementsClazz) {
        String json = toJson(o);
        return toObject(json, collectionClazz, elementsClazz);
    }

    /**
     * 获取工具类使用的 mapper
     *
     * @return ObjectMapper 实例
     */
    public static JsonMapper getMapper() {
        return MAPPER;
    }

}
