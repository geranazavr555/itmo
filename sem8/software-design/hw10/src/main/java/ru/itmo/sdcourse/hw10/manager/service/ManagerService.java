package ru.itmo.sdcourse.hw10.manager.service;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.itmo.sdcourse.hw10.core.dao.SubscriptionDao;
import ru.itmo.sdcourse.hw10.core.dao.UserDao;
import ru.itmo.sdcourse.hw10.core.model.Subscription;
import ru.itmo.sdcourse.hw10.core.model.User;
import ru.itmo.sdcourse.hw10.manager.model.UserSubscription;

import java.time.Clock;
import java.time.Duration;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManagerService {
    private static final Logger logger = LogManager.getLogger(ManagerService.class);

    private final SubscriptionDao subscriptionDao;
    private final UserDao userDao;
    private final Clock clock;

    public Optional<UserSubscription> findSubscription(long subscriptionId) {
        Optional<Subscription> subscriptionOpt = subscriptionDao.find(subscriptionId);
        if (subscriptionOpt.isEmpty())
            return Optional.empty();
        var subscription = subscriptionOpt.get();
        Optional<User> userOpt = userDao.find(subscription.userId());
        if (userOpt.isEmpty())
            throw new IllegalStateException("Unexpected case: found subscription without user [subscriptionId=" + subscriptionId + "]");
        return Optional.of(new UserSubscription(userOpt.get(), subscription));
    }

    public User registerUser(String someUserInfo) {
        logger.info("Register user " + someUserInfo);
        var user = new User(userDao.getFreeId(), someUserInfo);
        userDao.save(user);
        return user;
    }

    public Subscription issueSubscription(User user, Duration duration) {
        logger.info("Issue subscription for " + user + ", for " + duration);
        var now = clock.instant();
        var subscription = new Subscription(
                subscriptionDao.getFreeId(),
                user.id(),
                now,
                now.plus(duration)
        );
        subscriptionDao.save(subscription);
        return subscription;
    }

    public Subscription prolongSubscription(Subscription subscription, Duration duration) {
        logger.info("Prolong subscription " + subscription + ", for " + duration);
        var now = clock.instant();
        subscription = subscription.toBuilder().notAfter(now.plus(duration)).build();
        subscriptionDao.update(subscription);
        return subscription;
    }
}
