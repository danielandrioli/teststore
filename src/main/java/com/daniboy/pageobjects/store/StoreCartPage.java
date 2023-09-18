package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.store.components.CartItem;
import com.daniboy.pageobjects.store.components.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.function.Predicate;

public class StoreCartPage extends StoreBasePage {
    public static final String pageTitle = "Cart";
    @FindBy(css = ".cart-item")
    private List<WebElement> cartList;

    private WebDriver driver;

    public StoreCartPage(WebDriver driver) {
        super(driver, pageTitle);
        this.driver = driver;
    }

    private List<CartItem> getCartItens() {
        return cartList.stream().map(webElement -> new CartItem(webElement)).toList();
    }

    private CartItem getCartItem(Predicate<Product> condition) {
        return getCartItens().stream().filter(cartItem -> condition.test(cartItem.getProduct()))
                .findFirst().orElseThrow();
    }

    public int getItensQuantity() {
        int quantity = 0;
        for (CartItem item : getCartItens()) {
            quantity = quantity + item.getProduct().getQuantity();
        }
        return quantity;
    }

    public List<Product> getProductList() {
        return getCartItens().stream().map(cartItem -> cartItem.getProduct()).toList();
    }

    public StoreCartPage excludeProduct(Predicate<Product> condition) {
        getCartItem(condition).deleteItem();
        return this;
    }

    public StoreProductPage enterProductPage(Predicate<Product> condition) {
        Product product = getCartItem(condition).enterProductPage().getProduct();
        return new StoreProductPage(driver, product.getName());
    }

    public StoreCartPage increaseQuantity(Predicate<Product> condition) {
        getCartItem(condition).increaseQuantity();
        return this;
    }

    public StoreCartPage decreaseQuantity(Predicate<Product> condition) {
        getCartItem(condition).decreaseQuantity();
        return this;
    }
}
