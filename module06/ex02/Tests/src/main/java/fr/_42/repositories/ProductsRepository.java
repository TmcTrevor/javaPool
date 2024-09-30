package fr._42.repositories;

import fr._42.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
    List<Product> findAll();
    Optional<Product> findById(long id);
    void save(Product product);
    void update(Product product);
    void delete(Long id);
}
