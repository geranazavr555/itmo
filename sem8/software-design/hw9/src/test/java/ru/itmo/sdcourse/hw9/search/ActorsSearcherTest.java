package ru.itmo.sdcourse.hw9.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.itmo.sdcourse.hw9.search.actors.ActorsSearcher;
import ru.itmo.sdcourse.hw9.search.engine.SearchEngine;
import ru.itmo.sdcourse.hw9.search.fake.FakeClock;
import ru.itmo.sdcourse.hw9.search.fake.TimeoutSearchEngine;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActorsSearcherTest {
    @Mock
    private SearchEngine engine1;

    @Mock
    private SearchEngine engine2;

    @Mock
    private SearchEngine engine3;

    @Test
    public void noEngines() {
        var searcher = new ActorsSearcher();
        SearchResults results = searcher.search("abacaba", Duration.ofMillis(5000));
        assertTrue(results.results().isEmpty());
    }

    @Test
    public void searchCall() {
        var expected = new SearchResults(
                Duration.ZERO,
                List.of(
                        new SearchResult("engine1", "title1", "link1", "content1"),
                        new SearchResult("engine1", "title2", "link2", "content2"),
                        new SearchResult("engine1", "title3", "link3", "content3")
                )
        );
        when(engine1.search("abacaba")).thenReturn(expected);

        var searcher = new ActorsSearcher(new FakeClock(), engine1);
        SearchResults results = searcher.search("abacaba", Duration.ofMillis(5000));
        assertFalse(results.results().isEmpty());
        assertEquals(expected, results);
    }

    @Test
    public void manyEngines() {
        var engine1Result = new SearchResults(
                Duration.ZERO,
                List.of(
                        new SearchResult("engine1", "title1", "link1", "content1"),
                        new SearchResult("engine1", "title2", "link2", "content2"),
                        new SearchResult("engine1", "title3", "link3", "content3")
                )
        );
        when(engine1.search("abacaba")).thenReturn(engine1Result);
        when(engine1.search("kek")).thenReturn(new SearchResults(Duration.ZERO, Collections.emptyList()));

        var engine2Result = new SearchResults(
                Duration.ZERO,
                List.of(
                        new SearchResult("engine2", "title1", "link1", "content1"),
                        new SearchResult("engine2", "title2", "link2", "content2"),
                        new SearchResult("engine2", "title3", "link3", "content3")
                )
        );
        when(engine2.search("abacaba")).thenReturn(engine2Result);
        when(engine2.search("kek")).thenReturn(new SearchResults(Duration.ZERO, Collections.emptyList()));

        var engine3Result = new SearchResults(
                Duration.ZERO,
                List.of(
                        new SearchResult("engine3", "title1", "link1", "content1"),
                        new SearchResult("engine3", "title2", "link2", "content2"),
                        new SearchResult("engine3", "title3", "link3", "content3")
                )
        );
        when(engine3.search("abacaba")).thenReturn(engine3Result);
        when(engine3.search("kek")).thenReturn(new SearchResults(Duration.ZERO, Collections.emptyList()));

        var searcher = new ActorsSearcher(new FakeClock(), engine1, engine2, engine3);
        SearchResults results = searcher.search("abacaba", Duration.ofMillis(5000));
        assertFalse(results.results().isEmpty());
        assertEquals(9, results.results().size());

        var expected = Set.of(
                new SearchResult("engine1", "title1", "link1", "content1"),
                new SearchResult("engine1", "title2", "link2", "content2"),
                new SearchResult("engine1", "title3", "link3", "content3"),
                new SearchResult("engine2", "title1", "link1", "content1"),
                new SearchResult("engine2", "title2", "link2", "content2"),
                new SearchResult("engine2", "title3", "link3", "content3"),
                new SearchResult("engine3", "title1", "link1", "content1"),
                new SearchResult("engine3", "title2", "link2", "content2"),
                new SearchResult("engine3", "title3", "link3", "content3")
        );

        assertTrue(expected.containsAll(results.results()));
        assertTrue(results.results().containsAll(expected));

        results = searcher.search("kek", Duration.ofMillis(5000));
        assertTrue(results.results().isEmpty());
    }

    @Test
    public void timeoutAll() {
        var searcher = new ActorsSearcher(new TimeoutSearchEngine(Duration.ofSeconds(2), engine1));
        var results = searcher.search("kek", Duration.ofMillis(500));
        assertTrue(results.results().isEmpty());
    }

    @Test
    public void timeout() {
        var engine1Result = new SearchResults(
                Duration.ZERO,
                List.of(
                        new SearchResult("engine1", "title1", "link1", "content1"),
                        new SearchResult("engine1", "title2", "link2", "content2"),
                        new SearchResult("engine1", "title3", "link3", "content3")
                )
        );
        when(engine1.search("abacaba")).thenReturn(engine1Result);
        when(engine1.search("kek")).thenReturn(new SearchResults(Duration.ZERO, Collections.emptyList()));

        var engine3Result = new SearchResults(
                Duration.ZERO,
                List.of(
                        new SearchResult("engine3", "title1", "link1", "content1"),
                        new SearchResult("engine3", "title2", "link2", "content2"),
                        new SearchResult("engine3", "title3", "link3", "content3")
                )
        );
        when(engine3.search("abacaba")).thenReturn(engine3Result);
        when(engine3.search("kek")).thenReturn(new SearchResults(Duration.ZERO, Collections.emptyList()));

        var searcher = new ActorsSearcher(engine1, new TimeoutSearchEngine(Duration.ofSeconds(5), engine2), engine3);
        SearchResults results = searcher.search("abacaba", Duration.ofMillis(1000));
        assertFalse(results.results().isEmpty());
        assertEquals(6, results.results().size());

        var expected = Set.of(
                new SearchResult("engine1", "title1", "link1", "content1"),
                new SearchResult("engine1", "title2", "link2", "content2"),
                new SearchResult("engine1", "title3", "link3", "content3"),
                new SearchResult("engine3", "title1", "link1", "content1"),
                new SearchResult("engine3", "title2", "link2", "content2"),
                new SearchResult("engine3", "title3", "link3", "content3")
        );

        assertTrue(expected.containsAll(results.results()));
        assertTrue(results.results().containsAll(expected));

        results = searcher.search("kek", Duration.ofMillis(1000));
        assertTrue(results.results().isEmpty());
    }
}
