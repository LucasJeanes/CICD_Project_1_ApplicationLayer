package com.ie.cicd_project_1_applicationlayer.repository;

import com.ie.cicd_project_1_applicationlayer.Order;
import com.ie.cicd_project_1_applicationlayer.Product;
import com.ie.cicd_project_1_applicationlayer.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "service-layer", url = "http://localhost:8082")
public interface PortalServiceClient {

    //USER API METHODS-----
    @PostMapping("/users/register")
    String registerUser(@RequestBody User user);

    @GetMapping("/users")
    List<User> getAllUsers();

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id);

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User userDetails);

    @DeleteMapping("/users/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id);
    //-----USER API METHODS

    //PRODUCT API METHODS------
    @PostMapping("/products/newProduct")
    Product createProduct(@RequestBody Product product);

    @GetMapping("/products")
    List<Product> getAllProducts();

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Long id);

    @PutMapping("/products/{id}")
    Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails);

    @DeleteMapping("/products/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Long id);
    //----PRODUCT API METHODS

    //ORDER API METHODS-----
    @PostMapping("/orders/newOrder")
    String createOrder(@RequestBody Order order);

    @GetMapping("/orders")
    String getAllOrders();

    @GetMapping("/orders/{id}")
    String getOrderById(@PathVariable Long id);

    @GetMapping("/orders/user/{userId}")
    String getOrdersByUserId(@PathVariable Long userId);

    @PutMapping("/orders/{id}")
    String updateOrder(@PathVariable Long id, @RequestBody Order orderDetails);

    @DeleteMapping("/orders/{id}")
    String deleteOrder(@PathVariable Long id);
    //-----ORDER API METHODS
}
