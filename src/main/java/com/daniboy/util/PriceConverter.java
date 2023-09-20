package com.daniboy.util;

import org.openqa.selenium.WebElement;

abstract public class PriceConverter {

    public static double fromWebElementToDouble(WebElement element) {
        String priceStr = element.getText().replace("$", "");
        return Double.parseDouble(priceStr);
    }
}
