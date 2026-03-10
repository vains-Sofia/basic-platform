package com.basic.filter;

import com.basic.constant.AuthorizeConstants;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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

            Jwt jwt = jwtDecoder.decode(token);

            String jti = jwt.getId();

            Boolean exists = redisTemplate.hasKey(AuthorizeConstants.BLACKLIST_PREFIX + jti);

            if (Boolean.TRUE.equals(exists)) {
                response.setStatus(401);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}