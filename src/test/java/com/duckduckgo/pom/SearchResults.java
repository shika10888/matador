package com.duckduckgo.pom;

import com.duckduckgo.config.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {

  // Storing the elements along with their element locators into variables
  @FindBy(xpath = "//*[@id=\"duckbar_static\"]/li[1]/a")
  WebElement all;

  @FindBy(xpath = "//*[@id=\"duckbar_static\"]/li[2]/a")
  WebElement images;

  @FindBy(xpath = "//*[@id=\"duckbar_static\"]/li[3]/a")
  WebElement videos;

  @FindBy(xpath = "//*[@id=\"duckbar_static\"]/li[4]/a")
  WebElement news;

  @FindBy(xpath = "//*[@id=\"duckbar_static\"]/li[5]/a")
  WebElement maps;

  @FindBy(xpath = "//*[@id=\"duckbar_static\"]/li[6]/a")
  WebElement shopping;

  @FindBy(xpath = "//*[@id=\"duckbar_dropdowns\"]/li/div/a")
  WebElement settings;

  @FindBy(className = "module--images")
  WebElement searchImages;

  @FindBy(xpath = "//a[@data-testid='result-title-a']/span")
  List<WebElement> titles;

  //Initializing Page Elements
  public SearchResults() {
    PageFactory.initElements(Configuration.getDriver(), this);
  }

  // Making all the possible methods to perform actions on various elements
  public void clickAllLinks() {
    all.click();
  }

  public void clickImagesLinks() {
    images.click();
  }

  public void clickNewsLinks() {
    news.click();
  }

  public void clickMapsLinks() {
    maps.click();
  }

  public void clickShoppingLinks() {
    shopping.click();
  }

  public void clickSettingsLinks() {
    settings.click();
  }

  public List<String> getImagesFromSearch() {
    List<WebElement> images = searchImages.findElements(By.tagName("a"));

    List<String> imageUrls = new ArrayList<>();

    for (int i = 0; i < images.size(); i++) {
      imageUrls.add(images.get(i).getAttribute("href"));
      System.out.println(imageUrls.get(i));
    }
    return imageUrls;
  }

  public List<String> getTitles() {
    List<String> searchTitles = new ArrayList<>();

    for (WebElement title : titles) {
      searchTitles.add(title.getText());
    }
    return searchTitles;
  }
}
