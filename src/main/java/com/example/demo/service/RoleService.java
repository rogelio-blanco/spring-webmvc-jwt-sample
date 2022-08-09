package com.example.demo.service;

import com.example.demo.domain.Privilege;
import com.example.demo.domain.Role;
import com.example.demo.repository.PrivilegeRepository;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

//    public List<SimpleGrantedAuthority> getRolePrivileges(final List<GrantedAuthority> listOfRoles) {
//        var roles = roleRepository.findByNameIn(
//                listOfRoles.stream().map(GrantedAuthority::getAuthority).collect(toList()));
//
//
//        return roles.stream().map(Role::getPrivileges)
//                        .map(p -> p.stream().map(Privilege::getId).collect(toSet()))
//                .flatMap(Set::stream).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }
}
