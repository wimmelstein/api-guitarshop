package nl.inholland.apiguitarshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.inholland.apiguitarshop.jwt.JwtTokenProvider;
import nl.inholland.apiguitarshop.model.Brand;
import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.model.dto.GuitarDTO;
import nl.inholland.apiguitarshop.service.GuitarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GuitarController.class)
class GuitarControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuitarService guitarService;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private AuthenticationManager authenticationManagerBean;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void getAllGuitarsShouldReturnJsonArrayOfSizeOne() throws Exception {
        when(guitarService.getAllGuitars()).thenReturn(List.of(new Guitar(new Brand("Fender"), "Jazz", 1500)));
        this.mockMvc.perform(get("/guitars"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].brand.name").value("Fender"));
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void createGuitarShouldReturnStatusCreatedAndOneObject() throws Exception {
        Guitar guitar = new Guitar();
        guitar.setBrand(new Brand("Fender"));
        guitar.setModel("Cougar");
        GuitarDTO dto = new GuitarDTO();
        when(guitarService.createGuitar(any(GuitarDTO.class))).thenReturn(guitar);
        mockMvc.perform(post("/guitars")
                        .content(mapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand.name").value("Fender"))
                .andExpect(jsonPath("$.model").value("Cougar"));
    }
}