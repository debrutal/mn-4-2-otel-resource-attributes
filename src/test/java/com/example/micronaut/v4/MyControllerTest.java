package com.example.micronaut.v4;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
class MyControllerTest {

    @RegisterExtension
    static WireMockExtension wiremock = WireMockExtension.newInstance()
        .failOnUnmatchedRequests(true)
        .options(wireMockConfig().port(8880))
        .configureStaticDsl(true)
        .build();

    @Inject
    @Client("/")
    HttpClient httpClient;

    BlockingHttpClient blockingHttpClient;

    @BeforeEach
    void setUp() {
        blockingHttpClient = httpClient.toBlocking();
    }


    @Test
    void shouldFailWithDuplicateJson() {
        var request = HttpRequest.GET("/");
        var error = assertThrows(HttpClientResponseException.class, () -> blockingHttpClient.exchange(request, String.class));
        String expected = """
            {"type":"https://example.org/out-of-stock","title":"Out of Stock","status":400,"detail":"Item B00027Y5QG is no longer available","parameters":{"product":"B00027Y5QG"}}""";
        assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
        assertEquals(expected, error.getResponse().getBody().orElseThrow());
    }
}