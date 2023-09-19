package com.daniboy;

import com.daniboy.pageobjects.store.components.Product;

import java.util.ArrayList;
import java.util.List;

public enum ProductsToTest {
    PRODUCT_1 {
        @Override
        public Product getProduct() {
            Product product = new Product("Mug The Best Is Yet To Come", 11.9);
            return product;
        }
    },
    PRODUCT_2 {
        @Override
        public Product getProduct() {
            Product product = new Product("Hummingbird Printed Sweater", 28.72);
            product.setQuantity(2);
            List<String> variants = new ArrayList();
            variants.add("L");
            product.setVariants(variants);
            return product;
        }
    };

    abstract public Product getProduct();
}
