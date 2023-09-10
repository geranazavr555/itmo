package ru.itmo.sdcourse.hw9.search.engine;

import org.junit.Test;
import ru.itmo.sdcourse.hw9.search.SearchResult;
import ru.itmo.sdcourse.hw9.search.fake.FakeClock;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SearchEngineTest {
    @Test
    public void testDelay() {
        var expectedDuration = Duration.ofMillis(12345);
        var engine = new FakeDelayFileSystemSearchEngine(new FakeClock(), expectedDuration, Collections.emptyList());

        var results = engine.search("abacaba");

        assertEquals(expectedDuration, results.execDuration());
        assertTrue(results.results().isEmpty());
    }

    @Test
    public void testReturn() {
        var engine = new FakeDelayFileSystemSearchEngine(new FakeClock(), Duration.ZERO, List.of(
                SearchResult.of("title1", "link1", "content1"),
                SearchResult.of("title2", "link2", "content2"),
                SearchResult.of("title3", "link3", "content3")
        ));

        var results = engine.search("abacaba");

        assertFalse(results.results().isEmpty());
        assertEquals(List.of(
                new SearchResult(FakeDelayFileSystemSearchEngine.class.getSimpleName(),
                        "title1", "link1", "content1"),
                new SearchResult(FakeDelayFileSystemSearchEngine.class.getSimpleName(),
                        "title2", "link2", "content2"),
                new SearchResult(FakeDelayFileSystemSearchEngine.class.getSimpleName(),
                        "title3", "link3", "content3")
        ), results.results());
    }

    private static class FakeDelayFileSystemSearchEngine extends SearchEngine {
        private final Duration delay;
        private final FakeClock clock;
        private final List<SearchResult> results;

        public FakeDelayFileSystemSearchEngine(FakeClock clock, Duration delay, List<SearchResult> results) {
            super(clock);
            this.clock = clock;
            this.delay = delay;
            this.results = results;
        }

        @Override
        public String getName() {
            return getClass().getSimpleName();
        }

        @Override
        protected List<SearchResult> doSearch(String query) {
            clock.add(delay);
            return results;
        }
    }
}
