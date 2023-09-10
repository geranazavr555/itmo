package ru.itmo.sdcourse.hw9.search.engine.vk.response;

import java.util.List;

public record VkNewsfeedSearchResponse(List<Item> items) implements VkResponse {
    public record Item(long id, long ownerId, String text) {
    }
}
