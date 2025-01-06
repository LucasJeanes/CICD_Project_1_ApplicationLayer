package com.ie.cicd_project_1_applicationlayer;

import com.ie.cicd_project_1_applicationlayer.repository.PortalServiceClient;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final PortalServiceClient portalServiceClient;

    @Autowired
    public OrderController(PortalServiceClient portalServiceClient) {
        this.portalServiceClient = portalServiceClient;
    }

    @PostMapping("/newOrder")
    public ResponseEntity<String> createOrder(@Valid @RequestBody Order order) {
        String createdOrder = portalServiceClient.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<String> getAllOrders() {
        String orders = portalServiceClient.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderById(@PathVariable Long id) {
        try {
            String order = portalServiceClient.getOrderById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (FeignException.NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getOrdersByUserId(@PathVariable Long userId) {
        String orders = portalServiceClient.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@Valid @PathVariable Long id, @RequestBody Order orderDetails) {
        try {
            String updatedOrder = portalServiceClient.updateOrder(id, orderDetails);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (FeignException.NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        try {
            String deletedOrder = portalServiceClient.deleteOrder(id);
            return new ResponseEntity<>(deletedOrder, HttpStatus.NO_CONTENT);
        } catch (FeignException.NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
