package com.daniboy.pageobjects.store;

import org.openqa.selenium.WebDriver;

public class StoreCartPage extends StoreBasePage {
    public static final String pageTitle = "Cart";

    public StoreCartPage(WebDriver driver) {
        super(driver, pageTitle);
    }

    //todo criar métodos para excluir do carrinho e etc
}
