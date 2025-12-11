package com.example.controller;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 1. GET (Lista todos los productos)
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. GET (Obtiene un producto por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok) // Si se encuentra, devuelve 200 OK con el producto
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no se encuentra, devuelve 404 Not Found
    }

    // 3. POST (Crea un nuevo producto)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Devuelve 201 Created
    public Product createProduct(@RequestBody Product product) {
        // El ID se genera automáticamente por la BBDD (GenerationType.IDENTITY)
        return productRepository.save(product);
    }

    // 4. PUT (Modifica el precio)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductPrice(@PathVariable Long id, @RequestBody Product productUpdate) {
        
        return productRepository.findById(id)
                .map(existingProduct -> {
                    // Solo actualizamos el precio, como se indicó
                    if (productUpdate.getPrice() != null) {
                        existingProduct.setPrice(productUpdate.getPrice());
                    }
                    Product updatedProduct = productRepository.save(existingProduct);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. DELETE (Elimina un producto)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found
        }
    }
}