package com.project.Crud_Operation.repository;

import com.project.Crud_Operation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
