package com.backend_jenkins.Backend.repository;

import com.backend_jenkins.Backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer > {
}
