package com.example.controller;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Obtiene una lista paginada y filtrada de productos.
     * La ordenación por defecto y fija es por 'brand' (Marca) de forma ascendente.
     */
    @GetMapping
    public ResponseEntity<Page<Product>> getFilteredProducts(
            // Paginación (Parámetros opcionales)
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size,
            // Filtros de búsqueda (todos opcionales)
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double min_price,
            @RequestParam(required = false) Double max_price) {
        
        // 1. Definir la Ordenación Fija por Marca (Ascendente)
        // Eliminamos el parámetro 'sortBy' de la firma del método y lo fijamos aquí.
        Sort sort = Sort.by(Sort.Direction.ASC, "brand");
        
        // 2. Configurar la Paginación y la Ordenación (Pageable)
        Pageable pageable = PageRequest.of(page, size, sort);
        
        // 3. Construir la Especificación de Filtros
        Specification<Product> spec = ProductSpecification.filterProducts(brand, name, min_price, max_price);
        
        // 4. Ejecutar la consulta con el filtro, paginación y ordenación
        Page<Product> productPage = productRepository.findAll(spec, pageable);
        
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