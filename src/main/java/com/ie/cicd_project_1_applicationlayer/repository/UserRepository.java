package com.ie.cicd_project_1_applicationlayer.repository;

import com.ie.cicd_project_1_applicationlayer.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
