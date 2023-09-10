package ru.itmo.sdcourse.hw4.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itmo.sdcourse.hw4.model.User;
import ru.itmo.sdcourse.hw4.service.UserService;

public abstract class BaseController {
    private static final String USER_ID_SESSION_KEY = "userId";
    private static final String MESSAGE_SESSION_KEY = "message";
    private static final String MESSAGE_LEVEL_SESSION_KEY = "messageLevel";

    @Autowired
    private UserService userService;

    @ModelAttribute("currentUser")
    public User getCurrentUser(HttpSession httpSession) {
        return userService.find((Long) httpSession.getAttribute(USER_ID_SESSION_KEY)).orElse(null);
    }

    @ModelAttribute("message")
    public String getMessage(HttpSession httpSession) {
        String message = (String) httpSession.getAttribute(MESSAGE_SESSION_KEY);
        httpSession.removeAttribute(MESSAGE_SESSION_KEY);
        return message;
    }

    @ModelAttribute("messageLevel")
    public String getMessageLevel(HttpSession httpSession) {
        String messageLevel = (String) httpSession.getAttribute(MESSAGE_LEVEL_SESSION_KEY);
        httpSession.removeAttribute(MESSAGE_LEVEL_SESSION_KEY);
        return messageLevel;
    }

    void setUser(HttpSession httpSession, User user) {
        if (user != null) {
            httpSession.setAttribute(USER_ID_SESSION_KEY, user.getUserId());
        } else {
            unsetUser(httpSession);
        }
    }

    void unsetUser(HttpSession httpSession) {
        httpSession.removeAttribute(USER_ID_SESSION_KEY);
    }

    public void putMessage(HttpSession httpSession, MessageLevel messageLevel, String message) {
        httpSession.setAttribute(MESSAGE_SESSION_KEY, message);
        httpSession.setAttribute(MESSAGE_LEVEL_SESSION_KEY, messageLevel.name().toLowerCase());
    }

    public enum MessageLevel {
        SUCCESS, INFO, WARN, ERROR
    }
}
