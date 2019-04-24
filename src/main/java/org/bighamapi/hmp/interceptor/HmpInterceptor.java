package org.bighamapi.hmp.interceptor;

import org.bighamapi.hmp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HmpInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    //还没有经过handle
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (userService.findAdmin()==null){
            request.getRequestDispatcher(request.getContextPath()+"/init").forward(request,response);
        }
        return true;
    }

//    //经过handle之后还没有返回view
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
}
