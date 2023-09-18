package com.daniboy.pageobjects.store.components;

import java.util.List;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private List<String> variants;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }

    public List<String> getVariants() {
        return variants;
    }

}
