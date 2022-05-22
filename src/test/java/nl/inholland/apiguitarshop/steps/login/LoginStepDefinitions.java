package nl.inholland.apiguitarshop.steps.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import nl.inholland.apiguitarshop.model.dto.LoginDTO;
import nl.inholland.apiguitarshop.steps.BaseStepDefinitions;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Slf4j
public class LoginStepDefinitions extends BaseStepDefinitions implements En {

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final ObjectMapper mapper = new ObjectMapper();

    private ResponseEntity<String> response;
    private HttpEntity<String> request;

    public LoginStepDefinitions() {
        When("^the client calls /login with correct username\\/password$", () -> {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json");

            request = new HttpEntity<String>(mapper.writeValueAsString(
                    new LoginDTO("wim", "1q2w3e4r")),
                    httpHeaders);
            this.response = restTemplate.postForEntity(getBaseUrl() + "/login",
                    request, String.class);
        });

        Then("^the client receives a status of (\\d+)$", (Integer status) -> {
            Assertions.assertEquals(200, response.getStatusCodeValue());
        });

        And("^the client receives a JWT-token$", () -> {
            JSONObject jsonObject = new JSONObject(response.getBody());
            String token = jsonObject.getString("token");
            Assertions.assertTrue(token.startsWith("ey"));
        });
    }


}
