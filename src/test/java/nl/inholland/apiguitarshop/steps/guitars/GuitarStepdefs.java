package nl.inholland.apiguitarshop.steps.guitars;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java8.En;
import nl.inholland.apiguitarshop.model.dto.GuitarDTO;
import nl.inholland.apiguitarshop.steps.BaseStepDefinitions;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


public class GuitarStepdefs extends BaseStepDefinitions implements En {

    // Token is valid for one year , user has Role.USER
    private static final String VALID_TOKEN_USER = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6W10sImlhdCI6MTY1MzMxMTc0NiwiZXhwIjoxNjg0ODQ3NzQ2fQ.itSjs-evCYi2P7JAKwT4DY8u5RIASTghoaeQOa33v_s";
    private static final String VALID_TOKEN_ADMIN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOltdLCJpYXQiOjE2NTMzMTE4MjQsImV4cCI6MTY4NDg0NzgyNH0.heZJFGgEEdaUvEhbjnbK7PFfC_BfxOMIvmRq8fjvwMs";
    private static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6W10sImlhdCI6MTY1MzMxMTkwNSwiZXhwIjoxNjUzMzExOTA1fQ.mKFrXM15WCXVNbSFNpqYix_xsMjsH_M31hiFf-o7JXs";
    private static final String INVALID_TOKEN = "invalid";


    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final ObjectMapper mapper = new ObjectMapper();

    private ResponseEntity<String> response;
    private HttpEntity<String> request;

    private Integer status;
    private GuitarDTO dto;

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
        Given("^I have an expired token$", () -> {
            token = EXPIRED_TOKEN;
        });
        When("^I make a post request to the guitar endpoint$", () -> {
            httpHeaders.clear();
            httpHeaders.add("Authorization", "Bearer " + token);
            httpHeaders.add("Content-Type", "application/json");
            request = new HttpEntity<>(mapper.writeValueAsString(dto), httpHeaders);
            response = restTemplate.postForEntity(getBaseUrl() + "/guitars", request, String.class);
            status = response.getStatusCodeValue();
        });

        And("^I have a valid guitar object with brand \"([^\"]*)\" and model \"([^\"]*)\" and price (\\d+)$", (String brand, String model, Integer price) -> {
            dto = new GuitarDTO(brand, model, price);
        });
    }
}
