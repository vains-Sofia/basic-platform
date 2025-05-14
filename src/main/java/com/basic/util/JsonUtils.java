package com.basic.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
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

    private final static ObjectMapper MAPPER = new ObjectMapper();

    static {
        // 对象的所有字段全部列入，还是其他的选项，可以忽略null等
        MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 取消默认的时间转换为timeStamp格式
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置Date类型的序列化及反序列化格式
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 忽略空Bean转json的错误
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略未知属性，防止json字符串中存在，java对象中不存在对应属性的情况出现错误
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 添加java8序列化支持和新版时间对象序列化支持
        MAPPER.registerModule(new Jdk8Module());
        MAPPER.registerModule(new JavaTimeModule());
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
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            log.error("json转换失败,原因:", e);
        }
        return null;
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
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            log.error("json转换失败,原因:", e);
        }
        return null;
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
        try {
            return MAPPER.readValue(json, javaType);
        } catch (JacksonException e) {
            log.warn("deserialize json: {} to {} error {}", json, javaType, e.getMessage());
        }
        return null;
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
        try {
            return MAPPER.readValue(inputStream, clazz);
        } catch (IOException e) {
            log.error("json转换失败,原因:", e);
        }
        return null;
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
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(collectionClazz, elementsClazz);
            return MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            log.error("json转换失败,原因:", e);
        }
        return null;
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
        try {
            return o instanceof String s ? s : MAPPER.writeValueAsString(o);
        } catch (IOException e) {
            log.error("json转换失败,原因:", e);
        }
        return null;
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
     * 获取工具类使用的mapper
     *
     * @return ObjectMapper实例
     */
    public static ObjectMapper getMapper() {
        return MAPPER;
    }

}
