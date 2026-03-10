package com.basic.filter;

import com.basic.constant.AuthorizeConstants;
import com.basic.domain.Result;
import com.basic.util.ServletUtils;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 黑名单拦截器
 *
 * @author vains
 */
@Slf4j
@RequiredArgsConstructor
public class JwtBlacklistFilter extends OncePerRequestFilter {

    private final JwtDecoder jwtDecoder;

    private final RedisTemplate<String, Long> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain chain)
            throws ServletException, IOException {

        String auth = request.getHeader("Authorization");

        if (auth != null && auth.startsWith("Bearer ")) {

            String token = auth.substring(7);

            try {
                Jwt jwt = jwtDecoder.decode(token);

                String jti = jwt.getId();

                Boolean exists = redisTemplate.hasKey(AuthorizeConstants.BLACKLIST_PREFIX + jti);

                if (Boolean.TRUE.equals(exists)) {
                    response.setStatus(401);
                    ServletUtils.renderJson(response, Result.error(401, "登录已失效."));
                    return;
                }
            } catch (Exception e) {
                log.error("黑名单处理失败.", e);
                response.setStatus(401);
                ServletUtils.renderJson(response, Result.error(401, e.getMessage()));
                return;
            }
        }

        chain.doFilter(request, response);
    }
}