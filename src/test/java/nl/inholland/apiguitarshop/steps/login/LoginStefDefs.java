package nl.inholland.apiguitarshop.steps.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java8.En;
import nl.inholland.apiguitarshop.model.dto.LoginDTO;
import nl.inholland.apiguitarshop.steps.BaseStepDefinitions;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LoginStefDefs extends BaseStepDefinitions implements En {

    private HttpHeaders httpHeaders;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${nl.inholland.api.baseurl}")
    private String baseUrl;

    @LocalServerPort
    private int port;
    private final ObjectMapper mapper = new ObjectMapper();

    private ResponseEntity<String> entity;
    private HttpEntity<String> request;

    private String token;

    public LoginStefDefs() {
        When("^the client calls /login with correct username\\/password$", () -> {
            httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json");

            request = new HttpEntity<String>(mapper.writeValueAsString(new LoginDTO("wim", "1q2w3e4r")), httpHeaders);
            this.entity = restTemplate.postForEntity(baseUrl + port + "/login",
                    request, String.class);
        });

        Then("^the client receives a status of (\\d+)$", (Integer status) -> {
            Assertions.assertEquals(200, entity.getStatusCodeValue());
        });

        And("^the client receives a JWT-token$", () -> {
            this.token = entity.getBody();
            Assertions.assertNotNull(entity.getBody());
        });
    }


}
