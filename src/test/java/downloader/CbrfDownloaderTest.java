package downloader;

import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

class CbrfDownloaderTest {

    private MockServerClient mockServerClient;

    @BeforeEach
    void beforeEach(){
        mockServerClient = startClientAndServer(8081);
    }

    @DisplayName("smoke test")
    @Test
    void test_download() throws URISyntaxException, IOException {

        mockServerClient.when(
                HttpRequest.request()
                .withMethod("GET")
                .withPath("/currency_base/daily/")
        ).respond(
                HttpResponse.response()
                        .withBody(getFromResource("html/test.html")
                )
        );

        CbrfDownloader downloader = new CbrfDownloader();
        URL url = new URIBuilder()
                .setScheme("http")
                .setHost("localhost")
                .setPort(8081)
                .setPath("/currency_base/daily/")
                .build()
                .toURL();
        System.out.println(url);
        String actual = downloader.get(url,"#content table tr:contains(USD)");
        assertEquals("61,4140", actual);
    }

    // #content > table > tbody > tr:nth-child(1)

    private String getFromResource(String resName) throws URISyntaxException, IOException {

        return new String(
                Files.readAllBytes(
                        Paths.get(
                                getClass().getClassLoader().getResource(resName).toURI()
                        )
                )
        );
    }
}