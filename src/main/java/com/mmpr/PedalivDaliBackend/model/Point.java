package com.mmpr.PedalivDaliBackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "points")
@Data
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    private String address;
}
