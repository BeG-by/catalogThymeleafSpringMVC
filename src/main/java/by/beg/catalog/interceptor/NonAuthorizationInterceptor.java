package by.beg.catalog.interceptor;

import by.beg.catalog.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NonAuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User currentUser = (User) request.getSession().getAttribute("currentUser");

        if(currentUser == null){
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
