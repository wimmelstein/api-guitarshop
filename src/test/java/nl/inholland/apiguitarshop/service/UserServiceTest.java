package nl.inholland.apiguitarshop.service;

import nl.inholland.apiguitarshop.config.TestConfig;
import nl.inholland.apiguitarshop.jwt.JwtTokenProvider;
import nl.inholland.apiguitarshop.model.Role;
import nl.inholland.apiguitarshop.repository.UserRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class UserServiceTest {


    @Autowired
    private UserRepository userRepository;

    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private UserService service = new UserService(userRepository, authenticationManager, jwtTokenProvider, encoder);

    @BeforeClass
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin() {
        when(authenticationManager.authenticate(any())).thenReturn(new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(Role.ROLE_USER);
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        });

        String token = service.login("user", "password");
        Assertions.assertTrue(token.startsWith("ey"));

    }

}