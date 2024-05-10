package ru.krasnovm.interviewtask.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.krasnovm.interviewtask.dto.ProductDto;
import ru.krasnovm.interviewtask.entity.Product;
import ru.krasnovm.interviewtask.repository.ProductRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    private Product normalProduct;
    private Product longNameProduct;
    private Product longDescProduct;
    private Product negativePriceProduct;


    @BeforeEach
    void setUp() {
        normalProduct = Product.builder()
                .id(1L).name("Apple")
                .description("Apple desc")
                .price(12.5D)
                .inStock(true)
                .build();

        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < 300; i ++) {
            nameBuilder.append("a");
        }
        longNameProduct = Product.builder()
                .id(1L).name(nameBuilder.toString())
                .description("Apple desc")
                .price(12.5D)
                .inStock(true)
                .build();

        StringBuilder descBuilder = new StringBuilder();
        for (int i = 0; i < 5000; i ++) {
            descBuilder.append("a");
        }
        longDescProduct = Product.builder()
                .id(1L).name("Apple")
                .description(descBuilder.toString())
                .price(12.5D)
                .inStock(true)
                .build();

        negativePriceProduct = Product.builder()
                .id(1L).name("Apple")
                .description("Apple desc")
                .price(-12.5D)
                .inStock(true)
                .build();
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductDto dto = ProductDto.builder()
                .name(normalProduct.getName())
                .description(normalProduct.getDescription())
                .price(normalProduct.getPrice())
                .inStock(normalProduct.getInStock())
                .build();

        when(repository.save(any())).thenReturn(normalProduct);
        Product created = productService.create(dto);
        assertThat(created).isSameAs(normalProduct);
    }

    @Test
    void CreateShouldFailNameIsTooLong() throws Exception {
        ProductDto dto = ProductDto.builder()
                .name(longNameProduct.getName())
                .description(longNameProduct.getDescription())
                .price(longNameProduct.getPrice())
                .inStock(longNameProduct.getInStock())
                .build();

        Product created = productService.create(dto);
        assertThat(created).isSameAs(null);
    }

    @Test
    void CreateShouldFailDescriptionIsTooLong() throws Exception {
        ProductDto dto = ProductDto.builder()
                .name(longDescProduct.getName())
                .description(longDescProduct.getDescription())
                .price(longDescProduct.getPrice())
                .inStock(longDescProduct.getInStock())
                .build();

        Product created = productService.create(dto);
        assertThat(created).isSameAs(null);
    }

    @Test
    void CreateShouldFailPriceIsNegative() throws Exception {
        ProductDto dto = ProductDto.builder()
                .name(negativePriceProduct.getName())
                .description(negativePriceProduct.getDescription())
                .price(negativePriceProduct.getPrice())
                .inStock(negativePriceProduct.getInStock())
                .build();

        Product created = productService.create(dto);
        assertThat(created).isSameAs(null);
    }

    @Test
    void shouldUpdateProduct() {
        when(repository.save(normalProduct)).thenReturn(normalProduct);
        Product updated = productService.update(normalProduct);

        assertThat(updated).isSameAs(normalProduct);
    }

    @Test
    void UpdateShouldFailNameIsTooLong() {
        Product updated = productService.update(longNameProduct);
        assertThat(updated).isSameAs(null);
    }

    @Test
    void UpdateShouldFailDescriptionIsTooLong() {
        Product updated = productService.update(longDescProduct);
        assertThat(updated).isSameAs(null);
    }

    @Test
    void UpdateShouldFailPriceIsNegative() {
        Product updated = productService.update(negativePriceProduct);
        assertThat(updated).isSameAs(null);
    }
}