package ru.itmo.sdcourse.hw9.search.engine.vk.client;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

public class VkNewsfeedSearchClient extends VkClient {
    private static final String METHOD = "newsfeed.search";

    @Inject
    public VkNewsfeedSearchClient(@Named("vkSecure") boolean secure,
                                  @Named("vkHost") String host,
                                  @Named("vkPort") int port,
                                  @Named("vkAccessToken") String accessToken) {
        super(secure, host, port, METHOD, accessToken);
    }

    public String fetch(String query) {
        return super.fetch(Map.of("q", query));
    }
}
