package com.youcode.nowastebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Association {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String contactInfo;


}
