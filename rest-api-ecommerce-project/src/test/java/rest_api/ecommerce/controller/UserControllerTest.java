package rest_api.ecommerce.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rest_api.ecommerce.entity.User;
import rest_api.ecommerce.model.UserRequestRegister;
import rest_api.ecommerce.model.WebResponse;
import rest_api.ecommerce.repository.UserRepository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;



@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void registerSuccess() throws Exception{
        UserRequestRegister request = new UserRequestRegister();
        request.setUsername("test");
        request.setName("TEST");
        request.setPassword("rahasia");
        request.setEmail("ikroman9a@gmail.com");
        request.setPhone("0828282828");

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("OK",response.getData());
        });
    }

    @Test
    public void registerBadRequest() throws Exception{
        UserRequestRegister request = new UserRequestRegister();
        request.setUsername("test");
        request.setName("");
        request.setPassword("");
        request.setEmail("");
        request.setPhone("0828282828");

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getData());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    public void registerDuplicate() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("test");
        user.setName("TEST");
        user.setPassword("rahasia");
        user.setEmail("contoh@gmail.com");
        user.setPhone("0828282828");
        userRepository.save(user);

        UserRequestRegister request = new UserRequestRegister();
        request.setUsername("test");
        request.setName("TEST");
        request.setPassword("rahasia");
        request.setEmail("contoh@gmail.com");
        request.setPhone("0828282828");

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getData());
            assertNotNull(response.getErrors());
        });
    }
}
