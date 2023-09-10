package ru.itmo.sdcourse.hw10.manager.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itmo.sdcourse.hw10.core.dao.SubscriptionDao;
import ru.itmo.sdcourse.hw10.core.dao.UserDao;
import ru.itmo.sdcourse.hw10.manager.service.ManagerService;

import java.time.Duration;

@Controller
@AllArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    private final SubscriptionDao subscriptionDao;
    private final UserDao userDao;

    @GetMapping("/manager")
    public String managerPage() {
        return "manager";
    }

    @PostMapping("/info")
    @ResponseBody
    public String subscriptionInfo(long subscriptionId) {
        return String.valueOf(managerService.findSubscription(subscriptionId));
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerUser(String userInfo) {
        return String.valueOf(managerService.registerUser(userInfo));
    }

    @PostMapping("/issue")
    @ResponseBody
    public String issueSubscription(long userId, int days) {
        return String.valueOf(managerService.issueSubscription(
                userDao.find(userId).orElseThrow(),
                Duration.ofDays(days)
        ));
    }

    @PostMapping("/prolong")
    @ResponseBody
    public String prolongSubscription(long subscriptionId, int days) {
        return String.valueOf(managerService.prolongSubscription(
                subscriptionDao.find(subscriptionId).orElseThrow(),
                Duration.ofDays(days)
        ));
    }
}