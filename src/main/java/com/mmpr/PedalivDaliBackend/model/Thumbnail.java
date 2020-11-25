package com.mmpr.PedalivDaliBackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "thumbnail")
@Data
public class Thumbnail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String path;
}
