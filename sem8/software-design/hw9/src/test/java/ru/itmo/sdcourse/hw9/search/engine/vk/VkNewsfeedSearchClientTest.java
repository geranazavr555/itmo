package ru.itmo.sdcourse.hw9.search.engine.vk;

import com.xebialabs.restito.server.StubServer;
import org.glassfish.grizzly.http.Method;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.itmo.sdcourse.hw9.search.engine.vk.client.VkNewsfeedSearchClient;

import java.net.BindException;
import java.util.Random;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.Condition.*;
import static org.junit.Assert.assertEquals;

public class VkNewsfeedSearchClientTest {
    private int port;

    private StubServer stubServer;

    @Before
    public void createStubServer() {
        boolean bind = true;
        do {
            try {
                port = new Random().nextInt(20000, 65535);
                stubServer = new StubServer(port).run();
            } catch (RuntimeException e) {
                if (e.getCause() instanceof BindException)
                    bind = false;
                else
                    throw e;
            }
        } while (!bind);
    }

    @After
    public void shutdownStubServer() {
        stubServer.stop();
        stubServer = null;
    }

    @Test
    public void simple() {
        whenHttp(stubServer)
                .match(
                        method(Method.GET),
                        startsWithUri("/method/"),
                        parameter("q", "query"),
                        parameter("access_token", "abacaba"),
                        parameter("v", "5.131"),
                        custom(call -> call.getParameters().size() == 3)
                ).then(stringContent("OK"));

        var client = new VkNewsfeedSearchClient(false, "localhost", port, "abacaba");
        String response = client.fetch("query");
        assertEquals("OK", response);
    }
}
