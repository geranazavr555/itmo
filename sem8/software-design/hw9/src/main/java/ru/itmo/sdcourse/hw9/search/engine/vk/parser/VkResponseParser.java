package ru.itmo.sdcourse.hw9.search.engine.vk.parser;

import ru.itmo.sdcourse.hw9.search.engine.vk.response.VkResponse;

public interface VkResponseParser<T extends VkResponse> {
     T parse(String apiResponse);
}
