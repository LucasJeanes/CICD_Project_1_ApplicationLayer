package com.ie.cicd_project_1_applicationlayer;

import com.ie.cicd_project_1_applicationlayer.repository.PortalServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PortalServiceClient portalServiceClient;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User(1L,
                "test_user",
                "test@example.com",
                "testPass");

        when(portalServiceClient.registerUser(any(User.class)))
                .thenReturn("User " + testUser.getUsername() + " registered successfully. ID: " + testUser.getId());

        List<User> users = Collections.singletonList(testUser);
        when(portalServiceClient.getAllUsers())
                .thenReturn("Retrieved all " + users.size()
                        + " users. \n" + users);

        when(portalServiceClient.getUserById(1L))
                .thenReturn("Retrieved user with ID: " + testUser.getId()
                        + "\n " + testUser);

        when(portalServiceClient.updateUser(any(Long.class), any(User.class)))
                .thenReturn("User with ID: " + testUser.getId()
                        + " updated: \n " + testUser);

        when(portalServiceClient.deleteUser(1L))
                .thenReturn("User with ID: " + 1L + " deleted successfully");
    }

    @Test
    public void testRegisterUser() throws Exception {
        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"email\":\"testuser@example.com\"," +
                        "\"password\":\"testPass\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("User "
                        + testUser.getUsername() + " registered successfully. ID: "
                        + testUser.getId()));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("Retrieved all 1 users. \n" +
                        "[{\"id\":1,\"username\":\"testuser\",\"email\":\"test@example.com\"}]"));
    }

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Retrieved user with ID: "
                        + testUser.getId() + "\n " + testUser));
    }

    @Test
    public void testUpdateuser() throws Exception {
        User updatedUser = new User(1L,
                "updatedUser",
                "updated@example.com",
                "updatedPassword");

        when(portalServiceClient.updateUser(any(Long.class), any(User.class)))
                .thenReturn("User with ID: " + updatedUser.getId() +
                        "updated: \n" + updatedUser);

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"updateduser\"," +
                        "\"email\":\"updated@example.com\"," +
                        "\"password\":\"updatedPassword\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User with ID: " + updatedUser.getId() +
                        " updated: \n" +
                        "{\"id\":1," +
                        "\"username\":\"updateduser\"," +
                        "\"email\":\"updated@example.com\"," +
                        "\"password\":\"updatedPassword\"}"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent())
                .andExpect(content().string("User with ID: 1 deleted successfully"));
    }
}
