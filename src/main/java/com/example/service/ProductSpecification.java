package com.example.service;

import com.example.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    // Método estático que construye la consulta basada en los filtros
    public static Specification<Product> filterProducts(String brand, String name, Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. Filtrado por Marca (brand)
            if (brand != null && !brand.trim().isEmpty()) {
                // Filtra por LIKE (insensible a mayúsculas/minúsculas)
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + brand.toLowerCase() + "%"));
            }

            // 2. Filtrado por Nombre/Modelo (name)
            if (name != null && !name.trim().isEmpty()) {
                // Filtra por LIKE (insensible a mayúsculas/minúsculas)
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            // 3. Filtrado por Precio Mínimo (minPrice)
            if (minPrice != null) {
                // price >= minPrice
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            // 4. Filtrado por Precio Máximo (maxPrice)
            if (maxPrice != null) {
                // price <= maxPrice
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            
            // Combina todos los predicados con 'AND'
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
