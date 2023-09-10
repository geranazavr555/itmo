package ru.itmo.sdcourse.hw10.entrance.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itmo.sdcourse.hw10.entrance.service.EntranceService;

@Controller
@AllArgsConstructor
public class EntranceController {
    private final EntranceService entranceService;

    @GetMapping("/entrance")
    public String entrancePage(Model model) {
        model.addAttribute("subscriptionId", "");
        return "entrance";
    }

    @PostMapping("/enter")
    @ResponseBody
    public String enter(long subscriptionId) {
        return String.valueOf(entranceService.enter(subscriptionId));
    }

    @PostMapping("/exit")
    @ResponseBody
    public String exit(long subscriptionId) {
        return String.valueOf(entranceService.exit(subscriptionId));
    }
}
