package com.diva.basics.java8.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ProductPipeline {

    public static void main(String[] args) {
        List<Product> products =  new ArrayList<>();

         // Supplier: Provide default products if none exist
        Supplier<List<Product>> productSupplier = () -> List.of(
            new Product("iPhone", "Electronics", 1200, true),
            new Product("Shoes", "Fashion", 80, true),
            new Product("Laptop", "Electronics", 2000, false),
            new Product("Jacket", "Fashion", 150, true)
        );

        if(products.isEmpty()) {
            products = productSupplier.get();
        }

        Predicate<Product> expensiveProduct = (Product p) -> p.getPrice() > 100;

        Function<Product, ProductDTO> toDto = (Product p) -> new ProductDTO(p.getName(), p.getCategory());

        Consumer<ProductDTO> logProduct = (ProductDTO p) -> {
            System.out.println("Expensive product info :: " +p.productName+"-"+p.category);
        };

        products.stream().filter(expensiveProduct).map(toDto).forEach(logProduct);

    }

}

class ProductDTO {
    String productName;
    String category;

    public ProductDTO(String productName, String category) {
        this.productName = productName;
        this.category = category;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    
}

class Product {

    private String name;
    private String category;
    private double price;
    private boolean inStock;

    Product(String name, String category, double price, boolean inStock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    } 

    

}
