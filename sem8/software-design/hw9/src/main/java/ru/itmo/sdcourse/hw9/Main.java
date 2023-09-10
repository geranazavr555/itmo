package ru.itmo.sdcourse.hw9;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itmo.sdcourse.hw9.search.SearchResult;
import ru.itmo.sdcourse.hw9.search.SearchResults;
import ru.itmo.sdcourse.hw9.search.Searcher;
import ru.itmo.sdcourse.hw9.search.actors.ActorsSearcher;
import ru.itmo.sdcourse.hw9.search.engine.FileSystemSearchEngine;
import ru.itmo.sdcourse.hw9.search.engine.SearchEngine;
import ru.itmo.sdcourse.hw9.search.engine.VkNewsfeedSearchEngine;
import ru.itmo.sdcourse.hw9.search.engine.vk.client.VkNewsfeedSearchClient;
import ru.itmo.sdcourse.hw9.search.engine.vk.parser.VkNewsfeedSearchResponseParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String EOLN = System.lineSeparator();

    private final Searcher searcher;
    private final Duration timeout;

    public Main(Duration timeout, SearchEngine... engines) {
        searcher = new ActorsSearcher(engines);
        this.timeout = timeout;
    }

    public String run(String query) {
        SearchResults results = searcher.search(query, timeout);
        var builder = new StringBuilder("Time: ").append(results.execDuration()).append(EOLN);
        for (SearchResult result : results.results()) {
            builder.append("Engine: ").append(result.searchEngineName()).append(EOLN);
            builder.append("Title: ").append(result.title()).append(EOLN);
            builder.append("Link: ").append(result.link()).append(EOLN);
            builder.append("Content: ").append(result.content()).append(EOLN);
        }
        return builder.toString();
    }

    public void shutdown() {
        searcher.shutdown();
    }

    public static void main(String[] args) {
        var main = new Main(
                Duration.ofSeconds(5),
                new FileSystemSearchEngine(5, "C:\\Programing\\sem7"),
                new FileSystemSearchEngine(5, "Y:\\geranazavr555-projects"),
                new VkNewsfeedSearchEngine(
                        5,
                        new VkNewsfeedSearchClient(
                                true,
                                "api.vk.com",
                                443,
                                System.getenv("VK_ACCESS_TOKEN")
                        ),
                        new VkNewsfeedSearchResponseParser()
                )
        );

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String input = reader.readLine();
                if ("exit".equalsIgnoreCase(input))
                    return;

                System.out.println(main.run(input));
            }
        } catch (IOException e) {
            logger.error(e);
            System.exit(1);
        } finally {
            main.shutdown();
        }
    }
}
