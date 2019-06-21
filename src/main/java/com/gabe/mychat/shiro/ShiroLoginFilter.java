package com.gabe.mychat.shiro;

import com.alibaba.fastjson.JSONObject;


import com.gabe.mychat.pojo.user;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ShiroLoginFilter extends AdviceFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        user sysUser = (user) httpServletRequest.getSession().getAttribute("user");//重session中拿user
        if (null == sysUser && !StringUtils.contains(httpServletRequest.getRequestURI(), "/login.action")) {
            String requestedWith = httpServletRequest.getHeader("X-Request-With");
            if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定数据
                Map<String,Object> map = new HashMap<>();
                map.put("code", "-999");
                map.put("msg", "未登录");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json");
                httpServletResponse.getWriter().write(JSONObject.toJSONString(map));
                return false;
            } else {//不是ajax进行重定向处理
                System.out.println("登录拦截器");
                httpServletResponse.sendRedirect("/SHIRO/Login.html");//未登录重定向到这里
                return false;
            }
        }
        return true;
    }
}
