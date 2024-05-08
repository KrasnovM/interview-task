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
        repository.add(product);
        return product;
    }

    public List<Product> readAll() {
        return new LinkedList<>(repository);
    }

    public Product readByName(String name) {
        for (Product curr: repository) {
            if (curr.getName().equals(name)) {
                return curr;
            }
        }
        return null;
    }

    public Product update(Product product) {
        for (Product curr : repository) {
            if (curr.getId().equals(product.getId())) {
                curr = product;
                return product;
            }
        }
        return null;
    }

    public void delete (Product product) {
        repository.remove(product);
    }
}
