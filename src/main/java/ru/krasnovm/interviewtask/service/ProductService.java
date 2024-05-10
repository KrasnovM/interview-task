package ru.krasnovm.interviewtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.krasnovm.interviewtask.dto.ProductDto;
import ru.krasnovm.interviewtask.entity.Product;
import ru.krasnovm.interviewtask.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> searchByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        return productRepository.findByNameLike(name + "%");
    }

    public List<Product> searchByPrice(Double price) {
        if (price == null || price < 0) {
            return null;
        }
        return productRepository.findByPrice(price);
    }

    public List<Product> searchPriceHigher(Double price) {
        if (price == null || price < 0) {
            return null;
        }
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> searchPriceLower(Double price) {
        if (price == null || price < 0) {
            return null;
        }
        return productRepository.findByPriceLessThan(price);
    }

    public List<Product> searchInStock(Boolean inStock) {
        if (inStock == null) {
            return null;
        }
        return productRepository.findByInStock(inStock);
    }

    public  List<Product> sortByName(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getName)).
                collect(Collectors.toList());
    }

    public List<Product> sortByPrice(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getPrice)).
                collect(Collectors.toList());
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
