package com.mmpr.PedalivDaliBackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
