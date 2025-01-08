package com.ie.cicd_project_1_applicationlayer;

import com.ie.cicd_project_1_applicationlayer.repository.PortalServiceClient;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final PortalServiceClient portalServiceClient;

    @Autowired
    public ProductController(PortalServiceClient portalServiceClient) {
        this.portalServiceClient = portalServiceClient;
    }

    @PostMapping("/newProduct")
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product product) {
        if(product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("product name is required.");
        }
        if(product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }
        if(product.getQuantity() == null || product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity must be a non-negative integer.");
        }
        String createdProduct = portalServiceClient.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<String> getAllProducts() {
        String products = portalServiceClient.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable Long id) {
        try {
            String product = portalServiceClient.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (FeignException.NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@Valid @PathVariable Long id, @RequestBody Product productDetails) {
        try {
            String updatedProduct = portalServiceClient.updateProduct(id, productDetails);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (FeignException.NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            String response = portalServiceClient.deleteProduct(id);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (FeignException.NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
