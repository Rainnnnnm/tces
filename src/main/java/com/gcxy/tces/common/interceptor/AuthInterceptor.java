package com.gcxy.tces.common.interceptor;

import com.gcxy.tces.entity.LoginToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Rain
 * @date 2019/9/24
 */
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 前处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        LoginToken token = (LoginToken) session.getAttribute("loginToken");

        if(uri.contains("login")){
            return true;
        }

        if(token == null){
            return false;
        }

        String status = (String) token.getStatus().get("status");
        String auth = token.getAuth();

        if(!status.equals("201")){
            return false;
        }


        return true;
    }

    /**
     * 后处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
