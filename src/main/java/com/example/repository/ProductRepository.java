package com.example.repository;
import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Aquí puedes añadir métodos de búsqueda personalizados si los necesitas, 
    // pero para CRUD básico no es necesario añadir más código.
}