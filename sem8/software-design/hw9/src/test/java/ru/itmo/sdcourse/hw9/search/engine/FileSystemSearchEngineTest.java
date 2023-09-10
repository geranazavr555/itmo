package ru.itmo.sdcourse.hw9.search.engine;

import org.junit.Test;
import ru.itmo.sdcourse.hw9.search.SearchResult;

import java.util.Set;

import static org.junit.Assert.*;

public class FileSystemSearchEngineTest {
    @Test
    public void empty() {
        var engine = new FileSystemSearchEngine(1, "./src/test/resources/empty");
        var result = engine.doSearch("a");
        assertTrue(result.isEmpty());
    }

    @Test
    public void notExistingRoot() {
        var engine = new FileSystemSearchEngine(1, "./src/test/resources/abacaba");
        var result = engine.doSearch("a");
        assertTrue(result.isEmpty());
    }

    @Test
    public void limit() {
        var engine = new FileSystemSearchEngine(1, "./src/test/resources/simple");
        var result = engine.doSearch("public class");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void simple() {
        var engine = new FileSystemSearchEngine(Integer.MAX_VALUE, "./src/test/resources/simple");
        var result = engine.doSearch("public class");
        assertFalse(result.isEmpty());
        assertEquals(10, result.size());

        var titles = Set.of(
                "BraceState.java",
                "NumberState.java",
                "OperationState.java",
                "Tokenizer.java",
                "Main.java",
                "CalcVisitor.java",
                "PrintVisitor.java",
                "ParserVisitor.java",
                "EventsStatisticImpl.java"
        );
        for (SearchResult searchResult : result)
            assertTrue(titles.contains(searchResult.title()));
    }
}
