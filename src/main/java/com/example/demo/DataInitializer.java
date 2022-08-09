package com.example.demo;

import com.example.demo.domain.Privilege;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.domain.Vehicle;
import com.example.demo.repository.PrivilegeRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final VehicleRepository vehicles;
    
    private final UserRepository users;

    private final RoleRepository roles;

    private final PrivilegeRepository privileges;
    
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) {
        log.debug("initializing vehicles data...");
        Arrays.asList("moto", "car").forEach(v -> this.vehicles.saveAndFlush(Vehicle.builder().name(v).build()));
        
        log.debug("printing all vehicles...");
        this.vehicles.findAll().forEach(v -> log.debug(" Vehicle :" + v.toString()));

        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        final Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        final Role roleUser = createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
        
        this.users.save(User.builder()
                .username("user")
                .password(this.passwordEncoder.encode("password"))
                .enabled(true)
                .roles(Set.of(roleUser))
                .build()
        );
        
        this.users.save(User.builder()
                .username("admin")
                .password(this.passwordEncoder.encode("password"))
                .enabled(true)
                .roles(Set.of(roleUser, roleAdmin))
                .build()
        );
        
        log.debug("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }

    Privilege createPrivilegeIfNotFound(String name) {

        Optional<Privilege> privilege = privileges.findById(name);
        if (!privilege.isPresent()) {
            return privileges.save(Privilege.builder().id(name).build());
        }
        return privilege.get();
    }

    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Optional<Role> role = roles.findById(name);
        if (!role.isPresent()) {
            return roles.save(Role.builder().id(name).privileges(privileges).build());
        }
        return role.get();
    }
}
