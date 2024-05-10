package ru.krasnovm.interviewtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.krasnovm.interviewtask.dto.ProductDto;
import ru.krasnovm.interviewtask.entity.Product;
import ru.krasnovm.interviewtask.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(ProductDto productDto) {
        if (!validateProduct(productDto)) {
            return null;
        }

        return productRepository.save(Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .inStock(productDto.getInStock())
                .build());
    }

    public List<Product> readAll() {
        return productRepository.findAll();
    }

    public List<Product> readByName(String name) {
        return productRepository.readByName(name);
    }

    public Product update(Product product) { //check if working
        if (!validateProduct(product)) {
            return null;
        }

        return productRepository.save(product);
    }

    public void delete (Long id) {
        productRepository.deleteById(id);
    }

    private boolean validateProduct(Product product) {
        //Название товара ограничено 255 символами и оно обязательно при создании
        if (product.getName().length() > 255 ||
                product.getName().isEmpty()) {
            return false;
        }

        //Описание товара ограничено 4096 символами
        if (product.getDescription().length() > 4096) {
            return false;
        }

        //Цена товара не может быть меньше 0
        if (product.getPrice() != null && product.getPrice() < 0) {
            return false;
        }

        return true;
    }

    private boolean validateProduct(ProductDto productDto) {
        //Название товара ограничено 255 символами и оно обязательно при создании
        if (productDto.getName().length() > 255 ||
                productDto.getName().isEmpty()) {
            return false;
        }

        //Описание товара ограничено 4096 символами
        if (productDto.getDescription().length() > 4096) {
            return false;
        }

        //Цена товара не может быть меньше 0
        if (productDto.getPrice() != null && productDto.getPrice() < 0) {
            return false;
        }

        return true;
    }
}
