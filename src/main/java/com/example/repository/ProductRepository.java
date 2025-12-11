package com.example.repository;
import com.example.model.Product;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
// Ahora extiende dos interfaces:
// 1. JpaRepository: para CRUD básico (save, findById, delete)
// 2. JpaSpecificationExecutor: para consultas dinámicas y complejas
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Streamable<Order> findById(String id);
    // No necesitamos añadir métodos aquí; la funcionalidad la obtendremos
    // del JpaSpecificationExecutor y del nuevo Service/Controller.
}