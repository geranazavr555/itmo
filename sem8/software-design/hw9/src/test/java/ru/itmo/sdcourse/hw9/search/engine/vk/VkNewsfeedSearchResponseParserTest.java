package ru.itmo.sdcourse.hw9.search.engine.vk;

import com.google.common.io.Resources;
import com.google.gson.JsonSyntaxException;
import org.junit.Test;
import ru.itmo.sdcourse.hw9.search.engine.vk.exception.VkException;
import ru.itmo.sdcourse.hw9.search.engine.vk.parser.VkNewsfeedSearchResponseParser;
import ru.itmo.sdcourse.hw9.search.engine.vk.response.VkNewsfeedSearchResponse;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class VkNewsfeedSearchResponseParserTest {
    @Test
    public void ok() {
        VkNewsfeedSearchResponseParser parser = new VkNewsfeedSearchResponseParser();
        VkNewsfeedSearchResponse result = parser.parse(readTest("Ok"));

        assertEquals(4, result.items().size());
        assertTrue(result.items().get(0).text().contains("#drive"));
    }

    @Test(expected = VkException.class)
    public void apiError() {
        VkNewsfeedSearchResponseParser parser = new VkNewsfeedSearchResponseParser();
        parser.parse(readTest("ApiError"));
    }

    @Test(expected = JsonSyntaxException.class)
    public void malformedJson() {
        VkNewsfeedSearchResponseParser parser = new VkNewsfeedSearchResponseParser();
        parser.parse(readTest("MalformedJson"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void noFields() {
        VkNewsfeedSearchResponseParser parser = new VkNewsfeedSearchResponseParser();
        parser.parse(readTest("NoFields"));
    }

    private String readTest(String name) {
        URL resource = getClass().getResource("parserTest" + name + ".json");
        try {
            assert resource != null;
            return Resources.toString(resource, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
