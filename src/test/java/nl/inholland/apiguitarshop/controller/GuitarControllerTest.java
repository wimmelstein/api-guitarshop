package nl.inholland.apiguitarshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.inholland.apiguitarshop.config.TestConfig;
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
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GuitarController.class)
@Import(GuitarController.class)
@ContextConfiguration(classes = TestConfig.class)
class GuitarControllerTest {


    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private GuitarService guitarService;


    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void getAllGuitarsShouldReturnJsonArrayOfSizeOne() throws Exception {
        when(guitarService.getAllGuitars()).thenReturn(List.of(new Guitar(new Brand("Fender"), "Jazz", 1500)));
        this.mockMvc.perform(get("/guitars/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].brand.name").value("Fender"));
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = {"ADMIN"})
    void createGuitarWithRoleAdminShouldReturnStatusCreatedAndOneObject() throws Exception {
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

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void createGuitarWithRoleUserWillReturnUnauthorized() throws Exception {
        when(guitarService.createGuitar(any(GuitarDTO.class))).thenReturn(new Guitar());
        mockMvc.perform(post("/guitars/")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}