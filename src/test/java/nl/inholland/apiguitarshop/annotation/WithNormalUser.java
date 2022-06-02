package nl.inholland.apiguitarshop.annotation;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "user", password = "password", roles = "USER")
public @interface WithNormalUser {
}
