package ru.itmo.sdcourse.hw10.entrance.service;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.itmo.sdcourse.hw10.core.dao.AccessDao;
import ru.itmo.sdcourse.hw10.core.dao.SubscriptionDao;
import ru.itmo.sdcourse.hw10.core.model.Access;
import ru.itmo.sdcourse.hw10.core.model.Entrance;
import ru.itmo.sdcourse.hw10.core.model.Exit;
import ru.itmo.sdcourse.hw10.core.model.Subscription;

import java.time.Clock;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EntranceService {
    private static final Logger logger = LogManager.getLogger(EntranceService.class);

    private final SubscriptionDao subscriptionDao;
    private final AccessDao accessDao;
    private final Clock clock;

    public boolean enter(long subscriptionId) {
        Optional<Subscription> subscription = subscriptionDao.find(subscriptionId);
        if (subscription.isEmpty())
            return false;

        var now = clock.instant();
        if (now.isAfter(subscription.get().notAfter()))
            return false;

        List<Access> accesses = accessDao.findBySubscriptionId(subscriptionId);
        if (!accesses.isEmpty()) {
            if (accesses.get(accesses.size() - 1) instanceof Entrance){
                logger.warn("Suspicious entrance attempt by subscription with id " + subscriptionId);
                return false;
            }
        }

        logger.info("Entrance by subscription with id " + subscriptionId);
        accessDao.save(new Entrance(subscription.get().userId(), subscriptionId, now));
        return true;
    }

    public boolean exit(long subscriptionId) {
        Optional<Subscription> subscription = subscriptionDao.find(subscriptionId);
        if (subscription.isEmpty())
            return false;

        List<Access> accesses = accessDao.findBySubscriptionId(subscriptionId);
        if (accesses.isEmpty() || accesses.get(accesses.size() - 1) instanceof Exit) {
            logger.warn("Suspicious exit attempt by subscription with id " + subscriptionId);
            return false;
        }

        logger.info("Exit by subscription with id " + subscriptionId);
        accessDao.save(new Exit(subscription.get().userId(), subscriptionId, clock.instant()));
        return true;
    }
}
