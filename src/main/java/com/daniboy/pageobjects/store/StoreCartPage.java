package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.store.components.CartItem;
import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.util.PriceConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.function.Predicate;

public class StoreCartPage extends StoreBasePage {
    public static final String pageTitle = "Cart";
    @FindBy(css = ".cart-item")
    private List<WebElement> cartList;
    @FindBy(css = ".cart-total .value")
    private WebElement totalValue;
    @FindBy(css = "div#cart-subtotal-shipping > .value")
    private WebElement shippingCost;
    @FindBy(css = "input[name='discount_name']")
    private WebElement promoCodeField;
    @FindBy(css = "[action='http\\:\\/\\/teststore\\.automationtesting\\.co\\.uk\\/cart'] .btn-primary")
    private WebElement addPromoCodeBtn;
    @FindBy(css = ".promo-code-button .collapse-button")
    private WebElement havePromoCodeBtn;
    @FindBy(css = ".card-block.promo-name")
    private WebElement promoName;

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

    public double getTotalValue() {
        return PriceConverter.fromWebElementToDouble(totalValue);
    }

    public double getShippingCost() {
        return PriceConverter.fromWebElementToDouble(shippingCost);
    }

    public StoreCartPage clickOnHavePromoCode() {
        havePromoCodeBtn.click();
        return this;
    }

    public StoreCartPage enterPromoCode(String promoCode) {
        promoCodeField.sendKeys(promoCode);
        addPromoCodeBtn.click();
        wait.until(ExpectedConditions.visibilityOf(promoName));
        return this;
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
