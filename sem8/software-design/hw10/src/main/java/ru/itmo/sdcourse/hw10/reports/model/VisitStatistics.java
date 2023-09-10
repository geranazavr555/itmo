package ru.itmo.sdcourse.hw10.reports.model;

import ru.itmo.sdcourse.hw10.core.model.User;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;

public record VisitStatistics(Map<LocalDate, Integer> dayToCount, Map<User, Duration> userToAvgDuration) {
}
