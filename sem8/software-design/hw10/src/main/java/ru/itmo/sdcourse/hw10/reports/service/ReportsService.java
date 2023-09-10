package ru.itmo.sdcourse.hw10.reports.service;

import org.springframework.stereotype.Service;
import ru.itmo.sdcourse.hw10.core.dao.AccessDao;
import ru.itmo.sdcourse.hw10.core.dao.UserDao;
import ru.itmo.sdcourse.hw10.core.model.Entrance;
import ru.itmo.sdcourse.hw10.core.model.Exit;
import ru.itmo.sdcourse.hw10.core.model.User;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;
import ru.itmo.sdcourse.hw10.reports.model.VisitStatistics;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReportsService {
    private final UserDao userDao;
    private final Map<LocalDate, Integer> dayToCount = new ConcurrentHashMap<>();
    private final Map<Long, Duration> userIdToSumTime = new ConcurrentHashMap<>();
    private final Map<Long, Integer> userIdToCount = new ConcurrentHashMap<>();
    private final Map<Long, Instant> userIdToEntranceInstant = new ConcurrentHashMap<>();

    public ReportsService(AccessDao accessDao, UserDao userDao, Storage storage) {
        this.userDao = userDao;
        accessDao.findEntrances().forEach(this::handleEntrance);
        accessDao.findExits().forEach(this::handleExit);
        storage.registerHandler(Entrance.class, event -> handleEntrance((Entrance) event));
        storage.registerHandler(Exit.class, exit -> handleExit((Exit) exit));
    }

    public VisitStatistics getVisitStatistics() {
        Map<User, Duration> userToAvgDuration = new HashMap<>();
        for (Map.Entry<Long, Integer> userIdAndCount : userIdToCount.entrySet()) {
            var userId = userIdAndCount.getKey();
            var count = userIdAndCount.getValue();
            var avgDuration = userIdToSumTime.get(userId).dividedBy(count);
            userToAvgDuration.put(userDao.find(userId).orElseThrow(IllegalStateException::new), avgDuration);
        }

        return new VisitStatistics(
                Collections.unmodifiableMap(new TreeMap<>(dayToCount)),
                userToAvgDuration
        );
    }

    private void handleEntrance(Entrance entrance) {
        Instant timestamp = entrance.getTimestamp();
        LocalDate day = LocalDate.ofInstant(timestamp, ZoneId.systemDefault());
        dayToCount.compute(day, (key, count) -> count == null ? 1 : count + 1);

        userIdToEntranceInstant.put(entrance.getUserId(), timestamp);
    }

    private void handleExit(Exit exit) {
        var lastEntrance = userIdToEntranceInstant.get(exit.getUserId());
        if (lastEntrance == null)
            throw new IllegalStateException("Exit without entrance found: " + exit);

        var curDuration = Duration.between(lastEntrance, exit.getTimestamp());
        userIdToCount.compute(exit.getUserId(), (key, value) -> value == null ? 1 : value + 1);
        userIdToSumTime.compute(exit.getUserId(), (key, value) -> value == null ? curDuration : value.plus(curDuration));
    }
}
