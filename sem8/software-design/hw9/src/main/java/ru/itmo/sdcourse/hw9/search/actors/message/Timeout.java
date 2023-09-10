package ru.itmo.sdcourse.hw9.search.actors.message;

public enum Timeout implements Message {
    INSTANCE;

    public static Timeout get() {
        return INSTANCE;
    }
}
