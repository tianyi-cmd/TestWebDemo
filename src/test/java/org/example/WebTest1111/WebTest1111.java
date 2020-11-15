package org.example.WebTest1111;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebTest1111 {
    private static WebDriver driver = new ChromeDriver();
    @Test
    void testSearch(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com");
        driver.findElement(By.cssSelector(".search-dropdown .d-icon-search")).click();
        driver.findElement(By.cssSelector("#search-term")).sendKeys("ceshi");
    }

    @Test
    void testLogin() throws IOException, InterruptedException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");

        Thread.sleep(1500);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);
    }


    @Test
    void testLogined() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");
        if (file.exists()) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {

            };

            List<HashMap<String, Object>> cookies = mapper.readValue(file, typeReference);
            System.out.println(cookies);

            cookies.forEach(cookieMap -> {
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });
            driver.navigate().refresh();
        }else {
            testLogin();
        }
    }
}
