package org.bighamapi.hmp.interceptor;

import org.bighamapi.hmp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HmpInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Autowired
    CacheManager cacheManager;

    //还没有经过handle
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().indexOf(".")!=-1){
            return true;
        }
        if ("/init".equals(request.getRequestURI())){
            if (userService.findAdmin()!=null&& request.getMethod().equals("GET")){
                request.getRequestDispatcher("/").forward(request,response);
            }
            return true;
        }
        if (userService.findAdmin()==null){
                request.getRequestDispatcher(request.getContextPath()+"/init").forward(request,response);
        }
        return true;
    }
}
