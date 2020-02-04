package com.example.shopapplication.Repositories;

import com.example.shopapplication.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
