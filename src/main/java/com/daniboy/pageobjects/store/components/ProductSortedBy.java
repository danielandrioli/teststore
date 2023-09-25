package com.daniboy.pageobjects.store.components;

public enum ProductSortedBy {
    RELEVANCE("Relevance"),
    NAME_A_TO_Z("Name, A to Z"),
    NAME_Z_TO_A("Name, Z to A"),
    PRICE_L_TO_H("Price, low to high"),
    PRICE_H_TO_L("Price, high to low");


    private String linkText;
    ProductSortedBy(String linkText) {
        this.linkText = linkText;
    }

    public String getLinkText() {
        return linkText;
    }
}
