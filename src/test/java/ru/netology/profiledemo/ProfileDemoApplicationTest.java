package ru.netology.profiledemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProfileDemoApplicationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private static final GenericContainer<?> devAppContainer =
            new GenericContainer<>("devapp")
                    .withExposedPorts(8080);


    private static final GenericContainer<?> prodAppContainer =
            new GenericContainer<>("prodapp")
                    .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devAppContainer.start();
        prodAppContainer.start();
    }

    @Test
    void testDevApp() {
        String url = "http://localhost:" + devAppContainer.getMappedPort(8080);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals("Expected response for dev", response.getBody());
    }

    @Test
    void testProdApp() {
        String url = "http://localhost:" + prodAppContainer.getMappedPort(8081);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals("Expected response for prod", response.getBody());
    }
}