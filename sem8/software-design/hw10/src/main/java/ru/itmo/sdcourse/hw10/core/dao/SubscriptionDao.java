package ru.itmo.sdcourse.hw10.core.dao;

import org.springframework.stereotype.Component;
import ru.itmo.sdcourse.hw10.core.model.Subscription;
import ru.itmo.sdcourse.hw10.core.persistance.dao.AbstractDao;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class SubscriptionDao extends AbstractDao<Long, Subscription> {
    private final AtomicLong freeId;

    public SubscriptionDao(Storage storage) {
        super(storage, Subscription.class);
        freeId = new AtomicLong(find().stream().mapToLong(Subscription::id).max().orElse(0) + 1);
    }

    @Override
    public Long getFreeId() {
        return freeId.getAndIncrement();
    }
}
