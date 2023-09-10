package ru.itmo.sdcourse.hw10.core.persistance;

import java.io.Serializable;

public interface HasId<T extends Serializable> {
    T id();
}
