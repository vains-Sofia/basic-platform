package com.basic.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.StandardCharsets;

/**
 * web工具类
 *
 * @author vains
 */
@UtilityClass
public class ServletUtils {

    /**
     * 通过response写回json
     *
     * @param response 响应对象实例
     * @param data     要写回的数据
     */
    @SneakyThrows
    public static void renderJson(final HttpServletResponse response, final Object data) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JsonUtils.toJson(data));
        response.getWriter().flush();
    }

    /**
     * 获取当前请求
     *
     * @return 当前请求，获取失败会返回null
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra == null ? null : sra.getRequest();
    }

}
