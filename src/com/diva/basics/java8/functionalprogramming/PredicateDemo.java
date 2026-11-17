package com.diva.basics.java8.functionalprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateDemo {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("iPhone", "Electronics", 1200, true),
            new Product("Laptop", "Electronics", 2000, false),
            new Product("Shoes", "Fashion", 80, true),
            new Product("Smartwatch", "Electronics", 400, true),
            new Product("Jacket", "Fashion", 150, false)    
        );


        Predicate<Product> isExpensive = new Predicate<Product>() {
            public boolean test(Product p) {
                return p.getPrice() > 1000;
            }
        };

        Predicate<Product> lTest = p -> p.getPrice() > 1000;

        List<Product> expensiveProducts = products.stream()
            .filter(lTest)
            .collect(Collectors.toList());
        System.out.println(expensiveProducts);
    }
}

class Product {
    String name;
    String category;
    double price;
    boolean inStock;

    public Product(String name, String category, double price, boolean inStock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f [%s]", 
            name, category, price, inStock ? "Available" : "Out of stock");
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