package nl.inholland.apiguitarshop.steps.guitars;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import nl.inholland.apiguitarshop.steps.CucumberContextConfig;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = CucumberContextConfig.class)
@TestPropertySource("classpath:application-test.properties")
@Slf4j
public class GuitarStepdefs implements En {

    // Token is valid for one year , user has Role.USER
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3aW0iLCJhdXRoIjpbXSwiaWF0IjoxNjUzMTM3MjQxLCJleHAiOjE2ODQ2NzMyNDF9.aLRs7p9TQ-xTGCzt1SAP9swSmv6ob34GzBGEeRXvtXQ";
    private static final String INVALID_TOKEN = "invalid";

    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final String baseUrl = "https://localhost:";

    @LocalServerPort
    private int port;
    private final ObjectMapper mapper = new ObjectMapper();

    private ResponseEntity<String> entity;
    private HttpEntity<String> request;

    private Integer status;

    public GuitarStepdefs() {
        When("^I call the guitar endpoint with a valid token$", () -> {
            httpHeaders.add("Authorization", "Bearer " + VALID_TOKEN);
            request = new HttpEntity<>(null, httpHeaders);
            entity = restTemplate.exchange(baseUrl + port + "/guitars", HttpMethod.GET, new HttpEntity<>(null, httpHeaders), String.class);
        });

        Then("^the result is a list of guitars of size (\\d+)$", (Integer size) -> {
            int actual = JsonPath.read(entity.getBody(), "$.size()");
            Assertions.assertEquals(size, actual);

        });
        When("^the guitar endpoint is called with an invalid token$", () -> {
            httpHeaders.clear();
            httpHeaders.add("Authorization", INVALID_TOKEN);
            request = new HttpEntity<>(null, httpHeaders);
            entity = restTemplate.exchange(baseUrl + port + "/guitars", HttpMethod.GET, new HttpEntity<>(null, httpHeaders), String.class);
            status = entity.getStatusCodeValue();
        });

        Then("^the result is a status of (\\d+)$", (Integer code) -> {
            Assertions.assertEquals(code, status);
        });
    }
}
