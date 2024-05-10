package ru.krasnovm.interviewtask.dto;

public class ProductDto {
    private final String name;
    private final String description;
    private final Double price;
    private final Boolean inStock;

    private ProductDto(String name, String description, Double price, Boolean inStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }

    public static ProductDtoBuilder builder() {
        return new ProductDtoBuilder();
    }

    public static class ProductDtoBuilder {
        private String name;
        private String description;
        private Double price;
        private Boolean inStock;

        public ProductDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductDtoBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public ProductDtoBuilder inStock(Boolean inStock) {
            this.inStock = inStock;
            return this;
        }

        public ProductDto build() {
            //defaults
            if (inStock == null) {
                inStock = false;
            }
            if (price == null) {
                price = 0D;
            }

            return new ProductDto(name, description, price, inStock);
        }
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

        ProductDto that = (ProductDto) o;

        if (!getName().equals(that.getName())) return false;
        if (!getDescription().equals(that.getDescription())) return false;
        if (!getPrice().equals(that.getPrice())) return false;
        return getInStock().equals(that.getInStock());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getPrice().hashCode();
        result = 31 * result + getInStock().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }
}
