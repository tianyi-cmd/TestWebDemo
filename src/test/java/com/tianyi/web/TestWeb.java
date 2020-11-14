package com.tianyi.web;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestWeb {
    @Test
    void testCase1(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://ceshiren.com");
//        driver.getCurrentUrl();

    }

}
