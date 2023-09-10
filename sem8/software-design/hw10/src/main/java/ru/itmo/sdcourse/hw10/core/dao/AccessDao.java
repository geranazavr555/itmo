package ru.itmo.sdcourse.hw10.core.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.sdcourse.hw10.core.model.Access;
import ru.itmo.sdcourse.hw10.core.model.Entrance;
import ru.itmo.sdcourse.hw10.core.model.Exit;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AccessDao {
    private final Storage storage;

    public void save(Access access) {
        storage.append(access);
    }

    public List<Access> findBySubscriptionId(long subscriptionId) {
        return storage.find(event -> {
            if (event instanceof Access access)
                return access.getSubscriptionId() == subscriptionId;
            else
                return false;
        }).stream().map(Access.class::cast).collect(Collectors.toList());
    }

    public List<Entrance> findEntrances() {
        return storage.find(event -> Entrance.class.isAssignableFrom(event.getClass()))
                .stream().map(Entrance.class::cast).collect(Collectors.toList());
    }

    public List<Exit> findExits() {
        return storage.find(event -> Exit.class.isAssignableFrom(event.getClass()))
                .stream().map(Exit.class::cast).collect(Collectors.toList());
    }
}
