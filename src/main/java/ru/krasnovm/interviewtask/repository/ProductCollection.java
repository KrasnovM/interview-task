package ru.krasnovm.interviewtask.repository;

import org.springframework.stereotype.Repository;
import ru.krasnovm.interviewtask.entity.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductCollection {
    private final List<Product> repository;

    public ProductCollection() {
        this.repository = new LinkedList<>();
    }

    public void create(Product product) {
        repository.add(product);
    }

    public List<Product> readAll() {
        return new LinkedList<>(repository);
    }

    public Optional<Product> readByName(String name) {
        for (Product curr: repository) {
            if (curr.getName().equals(name)) {
                return Optional.of(curr);
            }
        }
        return Optional.empty();
    }

    public int update(Long id, Product product) {
        for (Product curr : repository) {
            if (curr.getId().equals(id)) {
                curr = product;
                return 0;
            }
        }
        return -1;
    }

    public void delete (Product product) {
        repository.remove(product);
    }
}
