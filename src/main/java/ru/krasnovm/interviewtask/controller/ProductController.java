package ru.krasnovm.interviewtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.krasnovm.interviewtask.dto.ProductDto;
import ru.krasnovm.interviewtask.entity.Product;
import ru.krasnovm.interviewtask.service.ProductService;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam(value = "searchType") String searchType,
                                                @RequestParam(value = "name") String name,
                                                @RequestParam(value = "price") Double price,
                                                @RequestParam(value = "inStock") Boolean inStock,
                                                @RequestParam(value = "sortType") String sortType) {

        List<Product> searchResult;
        switch (searchType) {
            case "NAME" -> searchResult = productService.searchByName(name);
            case "PRICE" -> searchResult = productService.searchByPrice(price);
            case "PRICELOWER" -> searchResult = productService.searchPriceLower(price);
            case "PRICEHIGHER" -> searchResult = productService.searchPriceHigher(price);
            case "INSTOCK" -> searchResult = productService.searchInStock(inStock);
            default -> searchResult = new LinkedList<>();
        }

        switch (sortType) {
            case "NAME" -> {
                return mappingResponseEntityList(productService.sortByName(searchResult));
            }
            case "PRICE" -> {
                return mappingResponseEntityList(productService.sortByPrice(searchResult));
            }
            default -> {
                return mappingResponseEntityList(searchResult);
            }
        }
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDto productDto) {
        return mappingResponseEntity(productService.create(productDto));
    }

    @GetMapping
    public ResponseEntity<List<Product>> readAll() {
        return mappingResponseEntityList(productService.readAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Product>> readByName(@PathVariable("name") String name) {
        return mappingResponseEntityList(productService.readByName(name));
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return mappingResponseEntity(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }

    private ResponseEntity<Product> mappingResponseEntity(Product product) {
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    private ResponseEntity<List<Product>> mappingResponseEntityList(List<Product> products) {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
