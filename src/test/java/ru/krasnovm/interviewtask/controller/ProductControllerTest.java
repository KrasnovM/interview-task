package ru.krasnovm.interviewtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.krasnovm.interviewtask.dto.ProductDto;
import ru.krasnovm.interviewtask.entity.Product;
import ru.krasnovm.interviewtask.service.ProductService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private List<Product> testProducts;
    private Product testApple;

    @BeforeEach
    void setUp() {
        testApple = Product.builder()
                .id(1L).name("Apple")
                .description("Apple desc")
                .price(12.5D).inStock(true)
                .build();

        testProducts = List.of(
                testApple,
                Product.builder().id(2L).name("Banana").description("Banana desc").build()
        );
    }

    @Test
    void shouldCreateProductWithAllFields() throws Exception {
        ProductDto testProductDto = ProductDto.builder()
                .name("Orange")
                .description("Orange desc")
                .price(114.5D)
                .inStock(true)
                .build();

        Product testProduct = Product.builder()
                .id(1L)
                .name("Orange")
                .description("Orange desc")
                .price(114.5D)
                .inStock(true)
                .build();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(testProductDto);

        String expectedResponse = """
               {
                   "id":1,
                   "name":"Orange",
                   "description":"Orange desc",
                   "price":114.5,
                   "inStock":true
               }
               """;

        when(productService.create(testProductDto)).thenReturn(testProduct);

        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse, false));
    }

    @Test
    void shouldCreateObjectWithoutDefiningPriceAndInStock() throws Exception{
        ProductDto testProductDto = ProductDto.builder()
                .name("Coffee")
                .description("Coffee desc")
                .build();

        Product testProduct = Product.builder()
                .id(4L).name("Coffee")
                .description("Coffee desc")
                .build();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(testProductDto);

        String expectedResponse = """
               {
                   "id":4,
                   "name":"Coffee",
                   "description":"Coffee desc",
                   "price":0.0,
                   "inStock":false
               }
               """;

        when(productService.create(testProductDto)).thenReturn(testProduct);

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse, false));
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        String expectedResponse = """
                [
                    {
                        "id":1,
                        "name":"Apple",
                        "description":"Apple desc",
                        "price":12.5,
                        "inStock":true
                    },
                    {
                        "id":2,
                        "name":"Banana",
                        "description":"Banana desc",
                        "price":0.0,
                        "inStock":false
                    }
                ]
                """;
        when(productService.readAll()).thenReturn(testProducts);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse, false));
    }

    @Test
    void shouldFindAppleByName() throws Exception {
        String expectedResponse = """
                [
                    {
                        "id":1,
                        "name":"Apple",
                        "description":"Apple desc",
                        "price":12.5,
                        "inStock":true
                    }
                ]
                """;

        when(productService.readByName("Apple")).thenReturn(List.of(testApple));
        mockMvc.perform(get("/products/Apple"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse, false));
    }

    @Test
    void shouldChangeAppleToCookie() throws Exception {
        Product cookie = Product.builder()
                .id(1L).name("Cookie")
                .description("Cookie desc")
                .price(34.23D)
                .inStock(true)
                .build();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(cookie);


        String expectedResponse = """
                {
                    "id":1,
                    "name":"Cookie",
                    "description":"Cookie desc",
                    "price":34.23,
                    "inStock":true
                }
                """;

        when(productService.update(cookie)).thenReturn(cookie);

        mockMvc.perform(
                        put("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse, false));
    }
}
