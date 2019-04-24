package org.bighamapi.hmp.interceptor;

import org.bighamapi.hmp.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login = request.getContextPath()+"/admin/login";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user==null){
            if (!login.equals(request.getRequestURI())){
                response.sendRedirect(login);
            }
        }else {
            if (!"admin".equals(user.getRole())){
                response.sendRedirect(login);
            }
            if (login.equals(request.getRequestURI())){
                response.sendRedirect(request.getContextPath()+"/admin");
            }
        }

        return true;
    }
}
