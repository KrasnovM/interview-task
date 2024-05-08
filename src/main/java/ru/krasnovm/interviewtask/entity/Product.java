package ru.krasnovm.interviewtask.entity;

import jakarta.persistence.Entity;

@Entity
public class Product {
    private final Long id;
    private final String name;
    private final String description;
    private final Double price;
    private final Boolean inStock;

    public Product(Long id, String name, String description, Double price, Boolean inStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }

    public static ProductBuilder builder(){
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private Long id;
        private String name;
        private String description;
        private Double price;
        private Boolean inStock;

        public ProductBuilder() {
        }

        public void id(Long id) {
            this.id = id;
        }

        public void name(String name) {
            this.name = name;
        }

        public void description(String description) {
            this.description = description;
        }

        public void price(Double price) {
            this.price = price;
        }

        public void inStock(Boolean inStock) {
            this.inStock = inStock;
        }

        public Product build() {
            //defaults
            if (inStock == null) {
                inStock = false;
            }
            if (price == null) {
                price = 0D;
            }

            return new Product(id, name, description, price, inStock);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getInStock() {
        return inStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!getId().equals(product.getId())) return false;
        if (!getName().equals(product.getName())) return false;
        if (!getDescription().equals(product.getDescription())) return false;
        if (!getPrice().equals(product.getPrice())) return false;
        return getInStock().equals(product.getInStock());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getPrice().hashCode();
        result = 31 * result + getInStock().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }
}
