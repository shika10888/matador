package com.duckduckgo.pom;

import com.duckduckgo.config.Configuration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search {

  // Storing the elements along with element locators into variables
  @FindBy(id = "searchbox_input")
  WebElement searchKeyWord;

  @FindBy(xpath = "//button[@type='submit']")
  WebElement searchButton;

  //Initializing Page Elements
  public Search() {
    PageFactory.initElements(Configuration.getDriver(), this);
  }

  // Method to search any keyword on Duckduckgo search engine
  public void searchString(String name) {
    searchKeyWord.sendKeys(name);
    searchButton.click();
  }
}
