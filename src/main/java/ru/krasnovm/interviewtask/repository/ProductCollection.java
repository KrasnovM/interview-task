package ru.krasnovm.interviewtask.repository;

import org.springframework.stereotype.Repository;
import ru.krasnovm.interviewtask.entity.Product;

import java.util.LinkedList;
import java.util.List;

@Repository
public class ProductCollection {
    private final List<Product> repository;

    public ProductCollection() {
        this.repository = new LinkedList<>();
    }

    public Product create(Product product) {
        Product toAdd = Product.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .inStock(product.getInStock())
                        .build();
        repository.add(toAdd);
        return toAdd;
    }

    public List<Product> readAll() {
        return new LinkedList<>(repository);
    }

    public List<Product> readByName(String name) {
        List<Product> result = new LinkedList<>();
        for (Product curr: repository) {
            if (curr.getName().equals(name)) {
                result.add(curr);
            }
        }
        return result;
    }

    public Product update(Product product) {
        for (Product curr : repository) {
            if (curr.getId().equals(product.getId())) {
                Product toAdd = Product.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .inStock(product.getInStock())
                        .build();
                curr = toAdd;
                return toAdd;
            }
        }
        return null;
    }

    public void delete (Long id) {
        repository.removeIf(p -> p.getId().equals(id));
    }
}
