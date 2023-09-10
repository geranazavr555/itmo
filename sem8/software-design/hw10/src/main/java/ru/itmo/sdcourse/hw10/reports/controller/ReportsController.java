package ru.itmo.sdcourse.hw10.reports.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmo.sdcourse.hw10.reports.model.VisitStatistics;
import ru.itmo.sdcourse.hw10.reports.service.ReportsService;

@Controller
@AllArgsConstructor
public class ReportsController {
    private final ReportsService reportsService;

    @GetMapping("/stat")
    public String statistics(Model model) {
        VisitStatistics visitStatistics = reportsService.getVisitStatistics();
        model.addAttribute("stat", visitStatistics);
        return "stat";
    }
}
