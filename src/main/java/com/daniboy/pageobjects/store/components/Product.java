package com.daniboy.pageobjects.store.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Product {
    private WebElement root;
    private By nameBy = By.cssSelector(".product-title");
    private By priceBy = By.cssSelector(".price");
    private By quickViewBy = By.cssSelector(".quick-view");

    public Product(WebElement root) {
        this.root = root;
    }

    public String getName() {
        return root.findElement(nameBy).getText();
    }

    public Double getPrice() {
        String price = root.findElement(priceBy).getText()
                .replace("$", "");
        return Double.parseDouble(price);
    }

    public Product clickOnQuickView(Actions action) { // Quick View aparece apenas após deixar mouse em cima. Sem isso, dá exception.
        action.moveToElement(root).perform(); // Shifts the mouse pointer to the center of the element
        root.findElement(quickViewBy).click();
        return this;
    } //acho que esse método deveria ser da página Store Home, e não do Product...
    //Acho q devo ter uma classe ProductFrame e nele ter (talvez) um Product (composição). No product, só ter atributos como String e double.
    //Afinal, .product-title e demais não existem na página do produto. Seriam só parte do frame.

    public void clickItem() {
        root.click();
    }
}
