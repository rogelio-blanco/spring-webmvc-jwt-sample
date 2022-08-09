package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicles")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle extends AbstractAuditableEntity<Long> implements Serializable {
    
    @Column
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Brand brand = Brand.FORD;
    
}
