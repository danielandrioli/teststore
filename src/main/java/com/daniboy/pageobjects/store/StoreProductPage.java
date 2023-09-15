package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.store.components.Product;
import com.daniboy.pageobjects.store.components.ProductFrame;
import org.openqa.selenium.WebDriver;

public class StoreProductPage extends StoreBasePage {
    private ProductFrame productFrame;

    public StoreProductPage(WebDriver driver, String productName) {
        super(driver, productName);
        productFrame = new ProductFrame(driver);
    }

    public ProductFrame getProductFrame() {
        return productFrame;
    }

//    public Product getProduct() { //talvez só deixar o getProductFrame
//        return productFrame.getProduct();
//    }

}
