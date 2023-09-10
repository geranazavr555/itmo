package ru.itmo.sdcourse.hw10.manager.model;

import ru.itmo.sdcourse.hw10.core.model.Subscription;
import ru.itmo.sdcourse.hw10.core.model.User;

public record UserSubscription(User user, Subscription subscription) {
}
