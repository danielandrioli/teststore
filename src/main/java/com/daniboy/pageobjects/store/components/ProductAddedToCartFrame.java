package com.daniboy.pageobjects.store.components;

import com.daniboy.pageobjects.store.StoreCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
    Obs: não há como pegar o seletor da variante do produto (tamanho, dimensão...). Ela é uma tag span apenas, e as vezes tem para um produto
    e não para outro.
 */
public class ProductAddedToCartFrame {
    @FindBy(css = ".product-name")
    private WebElement name;
    @FindBy(xpath = "//p[@class='product-price']")
    private WebElement productPrice;
    @FindBy(xpath = "//span[contains(., 'Quantity')]//strong") //xpath = "//span[text()='Quantity: ']//strong") funcionou tb, mas quero evitar NBSP.
    private WebElement quantity; // O número da quantidade mostrada nesse frame equivale a quantidade de produtos iguais a esse no carrinho.
    @FindBy(css = ".cart-content-btn .btn-primary")
    private WebElement btnProceedToCheckout;
    @FindBy(css = ".btn.btn-secondary")
    private WebElement btnContinueShopping;
    private Product product;
    private WebDriver driver;

    public ProductAddedToCartFrame(WebDriver driver) {
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(quantity));
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        this.driver = driver;
        createProduct();
    }

    private void createProduct() {
        String priceStr = productPrice.getText().replace("$", "");
        double price = Double.parseDouble(priceStr);
        this.product = new Product(name.getText(), price);
    }

    public Product getProduct() { // O produto obtido aqui é com css diferente do produto do ProductFrame. Por isso deve ter um getProduct aqui também.
        return product;             // Para checar se esse frame está nas conformidades com o produto que foi selecionado.
    }

    public void clickContinueShopping() {
        btnContinueShopping.click(); // Dependendo de que página o usuário veio, a page será diferente. Por isso do retorno void e não uma página.
    }

    public StoreCartPage clickProceedToCheckout() {
        btnProceedToCheckout.click();
        return new StoreCartPage(driver);
    }
}
