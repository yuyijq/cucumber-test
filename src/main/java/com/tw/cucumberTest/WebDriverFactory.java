package com.tw.cucumberTest;

import cuke4duke.annotation.After;
import cuke4duke.annotation.AfterAll;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Component
public class WebDriverFactory implements FactoryBean {
    private static Constructor<WebDriver> driverConstructor = getDriverConstructor();

    @SuppressWarnings("unchecked")
    private static Constructor<WebDriver> getDriverConstructor() {
        String driverName = System.getProperty("webdriver.impl", "org.openqa.selenium.firefox.FirefoxDriver");
        try {
            return (Constructor<WebDriver>) Thread.currentThread().getContextClassLoader().loadClass(driverName).getConstructor();
        } catch (Throwable problem) {
            problem.printStackTrace();
            throw new RuntimeException("Couldn't load " + driverName, problem);
        }
    }

    private WebDriver browser;

    public WebDriver getWebDriver() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        if (browser == null) {
            browser = driverConstructor.newInstance();
        }
        System.out.println(browser);
        return browser;
    }

    public Object getObject() throws Exception {
        return getWebDriver();
    }

    public Class<?> getObjectType() {
        return WebDriver.class;
    }

    public boolean isSingleton() {
        return true;
    }

    @After
    public void after_Scenario() {
        //in order to speed up the function test, I don't close browser for each scenario
        WebDriver.Options options = browser.manage();
        options.deleteAllCookies();
    }

    //I want to add an annotation(@AfterAll)
    public void closeBrowser(){
        browser.close();
        browser.quit();
    }
}
