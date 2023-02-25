package top.easyblog.titan.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.easyblog.titan.constant.Constants;
import top.easyblog.titan.exception.BusinessException;
import top.easyblog.titan.properties.AuthProperties;
import top.easyblog.titan.response.KhaosResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * 请求认证拦截器
 *
 * @author: frank.huang
 * @date: 2023-02-25 13:15
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AuthProperties authProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Start auth request.....");
        String token = request.getHeader(Constants.AUTH_TOKEN);
        String expireTime = redisTemplate.opsForValue().get(token);
        log.info("Get redis auth token {}={}", token, expireTime);
        if (noNeedValidAuth(request)) {
            return true;
        }
        boolean tokenExists = StringUtils.isNotBlank(expireTime) && Long.parseLong(expireTime) > 0;
        if (Objects.equals(tokenExists, Boolean.FALSE)) {
            throw new BusinessException(KhaosResultCode.AUTH_FAILED);
        }
        return true;
    }

    private boolean noNeedValidAuth(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return authProperties.getWhiteList().contains(requestURI);
    }
}
