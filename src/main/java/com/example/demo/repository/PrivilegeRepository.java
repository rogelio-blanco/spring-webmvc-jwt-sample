package com.example.demo.repository;

import com.example.demo.domain.Privilege;
import com.example.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, String> {
//    Privilege findByName(String name);
}
