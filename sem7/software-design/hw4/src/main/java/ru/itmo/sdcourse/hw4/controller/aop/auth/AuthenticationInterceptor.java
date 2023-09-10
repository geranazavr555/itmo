package ru.itmo.sdcourse.hw4.controller.aop.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.itmo.sdcourse.hw4.controller.BaseController;
import ru.itmo.sdcourse.hw4.model.User;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            AnonymousAllowed anonymousAllowed = handlerMethod.getMethodAnnotation(AnonymousAllowed.class);
            if (anonymousAllowed != null)
                return true;

            Class<?> controllerClass = handlerMethod.getMethod().getDeclaringClass();
            if (BaseController.class.isAssignableFrom(controllerClass)) {
                BaseController controllerInstance = (BaseController) applicationContext.getBean(controllerClass);
                HttpSession session = request.getSession();
                User currentUser = controllerInstance.getCurrentUser(session);
                if (currentUser == null) {
                    if ("get".equalsIgnoreCase(request.getMethod())) {
                        controllerInstance.putMessage(session, BaseController.MessageLevel.ERROR,
                                "You must be logged in to view this page");
                        response.sendRedirect("/enter?backUrl=" + URLEncoder.encode(request.getRequestURI(), StandardCharsets.UTF_8));
                    } else
                        response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }

                return true;
            }

            return true;
        }

        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
