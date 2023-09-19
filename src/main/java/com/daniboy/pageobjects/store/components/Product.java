package com.daniboy.pageobjects.store.components;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && quantity == product.quantity && name.equalsIgnoreCase(product.name) && Objects.equals(variants, product.variants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, variants);
    }
}
