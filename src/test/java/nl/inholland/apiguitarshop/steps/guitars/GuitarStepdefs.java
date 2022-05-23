package nl.inholland.apiguitarshop.steps.guitars;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java8.En;
import nl.inholland.apiguitarshop.steps.BaseStepDefinitions;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


public class GuitarStepdefs extends BaseStepDefinitions implements En {

    // Token is valid for one year , user has Role.USER
    private static final String VALID_TOKEN_USER = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3aW0iLCJhdXRoIjpbXSwiaWF0IjoxNjUzMTM3MjQxLCJleHAiOjE2ODQ2NzMyNDF9.aLRs7p9TQ-xTGCzt1SAP9swSmv6ob34GzBGEeRXvtXQ";
    private static final String VALID_TOKEN_ADMIN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3aW0iLCJhdXRoIjpbXSwiaWF0IjoxNjUzMjk0ODAxLCJleHAiOjE2NTMyOTg0MDF9.Iq1Gje7tEPVELyInuPvrh3zmxY_R0FA6yvNGns3Aoc8";
    private static final String INVALID_TOKEN = "invalid";

    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final ObjectMapper mapper = new ObjectMapper();

    private ResponseEntity<String> response;
    private HttpEntity<String> request;

    private Integer status;

    private String token;

    public GuitarStepdefs() {
        Then("^the result is a list of guitars of size (\\d+)$", (Integer size) -> {
            int actual = JsonPath.read(response.getBody(), "$.size()");
            Assertions.assertEquals(size, actual);

        });
        When("^I call the guitar endpoint$", () -> {
            httpHeaders.clear();
            httpHeaders.add("Authorization", "Bearer " + token);
            request = new HttpEntity<>(null, httpHeaders);
            response = restTemplate.exchange(getBaseUrl() + "/guitars", HttpMethod.GET, new HttpEntity<>(null, httpHeaders), String.class);
            status = response.getStatusCodeValue();
        });

        Then("^the result is a status of (\\d+)$", (Integer code) -> {
            Assertions.assertEquals(code, status);
        });
        Given("^I have a valid token for role \"([^\"]*)\"$", (String role) -> {
            switch (role) {
                case "user" -> token = VALID_TOKEN_USER;
                case "admin" -> token = VALID_TOKEN_ADMIN;
                default -> throw new IllegalArgumentException("No such role");
            }
        });
        Given("^I have an invalid token$", () -> {
            token = INVALID_TOKEN;
        });
    }
}
