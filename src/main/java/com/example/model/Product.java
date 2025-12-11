package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Data // Genera automáticamente getters, setters, toString, equals y hashCode (Lombok)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String name;

    private String brand;

    @Column(nullable = false)
    private Double price;

    @Column(name = "operating_system")
    private String operatingSystem;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "image_url")
    private String imageUrl;

    // Nota: Gracias a @Data (Lombok), no necesitamos escribir manualmente los constructores, getters y setters.
}
