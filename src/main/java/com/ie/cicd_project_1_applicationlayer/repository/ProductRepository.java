package com.ie.cicd_project_1_applicationlayer.repository;

import com.ie.cicd_project_1_applicationlayer.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
