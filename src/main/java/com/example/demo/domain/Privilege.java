package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name="privileges")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private String id;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
