package com.tw.cucumberTest;

import cuke4duke.annotation.After;
import cuke4duke.annotation.I18n;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DoGoogleSearchSteps {

    @Autowired
    WebDriver webDriver;

    @I18n.EN.Given("^I am on google home page$")
    public void i_open_google_home_page() {
        webDriver.get("http://www.google.com");
    }

    @When("^I do a search for keyword (.*)$")
    public void i_do_a_search_for_keyword(String keyword){
        webDriver.findElement(By.name("q")).sendKeys(keyword);
        webDriver.findElement(By.name("f")).submit();
    }

    @Then("^I can see the search result page for (.*)$")
    public void i_can_see_the_search_result_page(String keyword){
       webDriver.getTitle().contains(keyword);
    }
}