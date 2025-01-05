package com.ie.cicd_project_1_applicationlayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private double price;

    private String description;

    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;
}
