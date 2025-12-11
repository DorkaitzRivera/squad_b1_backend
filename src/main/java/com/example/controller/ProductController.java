package com.example.controller;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductSpecification; // Importamos la clase de especificación
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 1. GET (Busqueda Paginada, Filtrada y Ordenada)
    @GetMapping
    public ResponseEntity<Page<Product>> getFilteredProducts(
            // Paginación
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size,
            // Ordenación (siempre por marca, como se solicitó)
            @RequestParam(defaultValue = "brand") String sortBy,
            // Filtros de búsqueda (todos opcionales)
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double min_price,
            @RequestParam(required = false) Double max_price) {
        
        // 1. Configurar Paginación y Ordenación (Por Marca, ascendente)
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        
        // 2. Construir la Especificación de Filtros
        Specification<Product> spec = ProductSpecification.filterProducts(brand, name, min_price, max_price);
        
        // 3. Ejecutar la consulta
        Page<Product> productPage = productRepository.findAll(spec, pageable);
        
        // El objeto Page<Product> ya contiene toda la metadata de paginación
        return ResponseEntity.ok(productPage);
    }
    
    // ... (El resto de los métodos POST, GET/{id}, PUT/{id}, DELETE/{id} se mantienen igual) ...

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductPrice(@PathVariable Long id, @RequestBody Product productUpdate) {
        // ... (lógica de PUT) ...
        return productRepository.findById(id)
                .map(existingProduct -> {
                    if (productUpdate.getPrice() != null) {
                        existingProduct.setPrice(productUpdate.getPrice());
                    }
                    Product updatedProduct = productRepository.save(existingProduct);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}