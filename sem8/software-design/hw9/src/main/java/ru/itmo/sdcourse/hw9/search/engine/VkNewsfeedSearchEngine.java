package ru.itmo.sdcourse.hw9.search.engine;

import ru.itmo.sdcourse.hw9.search.SearchResult;
import ru.itmo.sdcourse.hw9.search.engine.vk.client.VkNewsfeedSearchClient;
import ru.itmo.sdcourse.hw9.search.engine.vk.parser.VkNewsfeedSearchResponseParser;

import java.util.List;
import java.util.stream.Collectors;

public class VkNewsfeedSearchEngine extends SearchEngine {
    private final VkNewsfeedSearchClient client;
    private final VkNewsfeedSearchResponseParser parser;
    private final int resultsLimit;

    public VkNewsfeedSearchEngine(int resultsLimit, VkNewsfeedSearchClient client, VkNewsfeedSearchResponseParser parser) {
        this.client = client;
        this.parser = parser;
        this.resultsLimit = resultsLimit;
    }

    @Override
    public String getName() {
        return "VK Newsfeed";
    }

    @Override
    protected List<SearchResult> doSearch(String query) {
        return parser.parse(client.fetch(query)).items().stream().map(item -> SearchResult.of(
                "VK Post " + item.ownerId() + "_" + item.id(),
                "https://vk.com/wall" + item.ownerId() + "_" + item.id(),
                item.text()
        )).limit(resultsLimit).collect(Collectors.toList());
    }
}
