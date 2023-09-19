package com.daniboy.pageobjects.store.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartItem {
    private WebElement root;
    private By nameBy = By.cssSelector("li > div > div > div > [href]");
    private By priceTotalBy = By.cssSelector(".product-price >strong");
    private By priceUnitBy = By.cssSelector(".current-price > .price");
    private By removeBy = By.cssSelector(".remove-from-cart");
    private By decreaseQtBy = By.cssSelector(".js-decrease-product-quantity.js-touchspin");
    private By increaseQtBy = By.cssSelector(".js-increase-product-quantity.js-touchspin");
    private By quantityBy = By.cssSelector("input[name='product-quantity-spin']");
    private By variantsBy = By.cssSelector(".product-line-info .value");
    private Product product;

    public CartItem(WebElement root) {
        this.root = root;
        String productName = root.findElement(nameBy).getText();
        String priceTotalStr = root.findElement(priceUnitBy).getText().replace("$", "");
        double productPrice = Double.parseDouble(priceTotalStr);
        int quantity = Integer.parseInt(root.findElement(quantityBy).getAttribute("value"));
        List<String> variants = root.findElements(variantsBy).stream().map(
                webElement -> webElement.getText()
        ).toList();
        product = new Product(productName, productPrice);
        product.setQuantity(quantity);
        product.setVariants(variants);
    }

    public Product getProduct() { return product; }

    public void deleteItem() {
        root.findElement(removeBy).click();
    }

    public CartItem increaseQuantity() {
        root.findElement(increaseQtBy).click();
        return this;
    }

    public CartItem decreaseQuantity() {
        root.findElement(decreaseQtBy).click();
        return this;
    }

    public CartItem enterProductPage() {
        root.findElement(nameBy).click();
        return this;
    }
}
