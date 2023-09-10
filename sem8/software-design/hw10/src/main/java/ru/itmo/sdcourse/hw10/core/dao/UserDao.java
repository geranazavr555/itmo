package ru.itmo.sdcourse.hw10.core.dao;

import org.springframework.stereotype.Component;
import ru.itmo.sdcourse.hw10.core.model.User;
import ru.itmo.sdcourse.hw10.core.persistance.dao.AbstractDao;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserDao extends AbstractDao<Long, User> {
    private final AtomicLong freeId;

    public UserDao(Storage storage) {
        super(storage, User.class);
        freeId = new AtomicLong(find().stream().mapToLong(User::id).max().orElse(0) + 1);
    }

    @Override
    public Long getFreeId() {
        return freeId.getAndIncrement();
    }
}
