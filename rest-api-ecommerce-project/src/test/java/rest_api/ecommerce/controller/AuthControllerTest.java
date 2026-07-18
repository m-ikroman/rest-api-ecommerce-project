package rest_api.ecommerce.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rest_api.ecommerce.entity.User;
import rest_api.ecommerce.model.LoginRequest;
import rest_api.ecommerce.model.TokenResponse;
import rest_api.ecommerce.model.WebResponse;
import rest_api.ecommerce.repository.UserRepository;
import rest_api.ecommerce.security.BCrypt;
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
public class AuthControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void loginUserNotFound()throws Exception{
        LoginRequest request = new LoginRequest();
        request.setUsername("test2");
        request.setPassword("1234");

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void loginUserPasswordUsernameWrong()throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("test");
        user.setName("TEST");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setEmail("contoh@gmail.com");
        user.setPhone("0828282828");
        userRepository.save(user);

        LoginRequest request = new LoginRequest();
        request.setUsername("test2");
        request.setPassword("1234");

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void loginUserSuccess()throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("test");
        user.setName("TEST");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setEmail("contoh@gmail.com");
        user.setPhone("0828282828");
        userRepository.save(user);

        LoginRequest request = new LoginRequest();
        request.setUsername("test");
        request.setPassword("rahasia");

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());

        });
    }

    @Test
    void logoutFailed() throws Exception{
        mockMvc.perform(
                delete("/api/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void logoutSuccess() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("test");
        user.setName("TEST");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setEmail("contoh@gmail.com");
        user.setPhone("0828282828");
        user.setToken("token");
        user.setTokenExpiredAt(System.currentTimeMillis() + 10000000L);

        userRepository.save(user);

        mockMvc.perform(
                delete("/api/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-TOKEN-API", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals("OK",response.getData());

            User userDb = userRepository.findFirstByUsername("test").orElse(null);
            assertNull(userDb.getTokenExpiredAt());
            assertNull(userDb.getToken());
        });
    }

}
