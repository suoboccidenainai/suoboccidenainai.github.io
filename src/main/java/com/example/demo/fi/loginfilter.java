package com.example.demo.fi;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.resu;
import com.example.demo.utils.jwtutil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class loginfilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        log.info("请求：{}", url);

        if (url.contains("login")
                || url.contains("up")
                || url.contains("forget")
                || url.contains("manager")
                || url.contains("js")
                || url.contains("css")
                || url.contains("jpg")
                || url.contains("html")
                || url.contains("ico")
                || url.contains("add")) {
            log.info("不用校验，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String jwt = request.getHeader("token");
            if (!StringUtils.hasLength(jwt)) {
            log.info("token为空");
            resu error = resu.error("not login");
            String jsstr = JSONObject.toJSONString(error);
            response.getWriter().write(jsstr);
            return;
        }
        try {
            jwtutil.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录错误信息");
            resu error = resu.error("1");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }

        log.info("令牌合法, 放行");
        filterChain.doFilter(request, response);
    }
}
