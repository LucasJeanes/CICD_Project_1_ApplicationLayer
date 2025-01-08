package com.ie.cicd_project_1_applicationlayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ie.cicd_project_1_applicationlayer.repository.PortalServiceClient;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final PortalServiceClient portalServiceClient;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(PortalServiceClient portalServiceClient) {
        this.portalServiceClient = portalServiceClient;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        logger.info("Inputted user registration: {}", user);
        String response;

        try {
            response = portalServiceClient.registerUser(user);
            logger.info("User registration successful: {}", response);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error during user registration: {}", e.getMessage(), e);
            return new ResponseEntity<>("Registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<String> getAllUsers() {
        String users = portalServiceClient.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Long id) {
        try {
            String user = portalServiceClient.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (FeignException.NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@Valid @PathVariable Long id, @RequestBody User userDetails) {
        String updatedUser = portalServiceClient.updateUser(id, userDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            String response = portalServiceClient.deleteUser(id);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (FeignException.NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
