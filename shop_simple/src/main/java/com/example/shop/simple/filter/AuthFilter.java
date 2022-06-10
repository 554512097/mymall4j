package com.example.shop.simple.filter;

import cn.hutool.core.util.StrUtil;
import com.example.shop.simple.auth.AuthConfigAdapter;
import com.example.shop.simple.exception.ShopBindException;
import com.example.shop.simple.manager.TokenStore;
import com.example.shop.simple.manager.handler.HttpHandler;
import com.example.shop.simple.pojo.bo.UserInfoInTokenBO;
import com.example.shop.simple.utils.AuthUserContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author: kevin
 * Date: 2022/6/9
 * Description:
 */
@Component
public class AuthFilter implements Filter {

    private final AuthConfigAdapter authConfigAdapter;
    private final TokenStore tokenStore;
    private final HttpHandler httpHandler;

    public AuthFilter(AuthConfigAdapter authConfigAdapter,
                      TokenStore tokenStore,
                      HttpHandler httpHandler) {
        this.authConfigAdapter = authConfigAdapter;
        this.tokenStore = tokenStore;
        this.httpHandler = httpHandler;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestUri = req.getRequestURI();
        List<String> excludePathPatterns = authConfigAdapter.excludePathPatterns();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String excludePathPattern : excludePathPatterns) {
            if (pathMatcher.match(excludePathPattern, requestUri)) {
                chain.doFilter(req, resp);
                return;
            }
        }
        String accessToken = req.getHeader("Authorization");
        // 也许需要登录，不登陆也能用的uri
        boolean mayAuth = pathMatcher.match(AuthConfigAdapter.MAYBE_AUTH_URI, requestUri);
        UserInfoInTokenBO userInfoInToken = null;
        try {
            // 如果有token，就要获取token
            if (StrUtil.isNotBlank(accessToken)) {
                userInfoInToken = tokenStore.getUserInfoByAccessToken(accessToken, true);
            } else if (!mayAuth) {
                // 返回前端401
                httpHandler.printServerResponseToWeb(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED.value());
                return;
            }
            // 保存上下文
            AuthUserContext.set(userInfoInToken);

            chain.doFilter(req, resp);

        } catch (Exception e) {
            // 手动捕获下非controller异常
            if (e instanceof ShopBindException) {
                httpHandler.printServerResponseToWeb(e.getMessage(), ((ShopBindException) e).getHttpStatusCode());
            } else {
                throw e;
            }
        } finally {
            AuthUserContext.clean();
        }

        chain.doFilter(request, response);
    }
}
