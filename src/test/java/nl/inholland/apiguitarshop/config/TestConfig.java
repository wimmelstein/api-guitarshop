package nl.inholland.apiguitarshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class TestConfig {

    public static final String VALID_TOKEN_USER = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6W10sImlhdCI6MTY1MzMxMTc0NiwiZXhwIjoxNjg0ODQ3NzQ2fQ.itSjs-evCYi2P7JAKwT4DY8u5RIASTghoaeQOa33v_s";
    public static final String VALID_TOKEN_ADMIN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOltdLCJpYXQiOjE2NTMzMTE4MjQsImV4cCI6MTY4NDg0NzgyNH0.heZJFGgEEdaUvEhbjnbK7PFfC_BfxOMIvmRq8fjvwMs";
    public static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6W10sImlhdCI6MTY1MzMxMTkwNSwiZXhwIjoxNjUzMzExOTA1fQ.mKFrXM15WCXVNbSFNpqYix_xsMjsH_M31hiFf-o7JXs";
    public static final String INVALID_TOKEN = "invalid";

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }

//    @Bean
//    public AuthenticationManager authenticationManager() {
//        return Mockito.mock(AuthenticationManager.class);
//    }

//    @Bean
//    public UserRepository userRepository() {
//        return Mockito.mock(UserRepository.class);
//    }
}
