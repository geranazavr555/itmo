package ru.itmo.sdcourse.hw10.core.persistance.event;

import java.io.Serializable;

public sealed interface Event extends Serializable permits AddEvent, ChangeEvent, DeleteEvent {
}
