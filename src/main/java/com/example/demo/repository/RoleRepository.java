package com.example.demo.repository;

import com.example.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
//    Role findByName(String name);
//    List<Role> findByNameIn(List<String> name);
}
