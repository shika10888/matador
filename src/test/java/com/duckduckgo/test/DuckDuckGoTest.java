package com.duckduckgo.test;

import com.duckduckgo.config.Configuration;
import com.duckduckgo.listener.TestFailureListener;
import com.duckduckgo.pom.Search;
import com.duckduckgo.pom.SearchResults;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Listeners(TestFailureListener.class)
public class DuckDuckGoTest {

  private List<String> imageUrls;
  private List<String> searchTitles;

  @BeforeTest
  public void setUp() throws InterruptedException {
    Configuration.getProperties();
    Configuration.getDriver();
    Search search = new Search();
    search.searchString("nice cars");
    Thread.sleep(2000);
    SearchResults results = new SearchResults();
    List<String> unrefinedUrls = results.getImagesFromSearch();

    imageUrls = refineUrls(unrefinedUrls);
    searchTitles = results.getTitles();
  }

  public List<String> refineUrls(List<String> unrefinedUrls) {
    List<String> refinedUrls = new ArrayList<>();
    for (String url : unrefinedUrls) {
      if (url.contains("&iai=")) {
        refinedUrls.add(url.split("&iai=")[1]);
      }
    }
    return refinedUrls;
  }

  @Test
  public void testImageUrlContainsDomain() {
    String domain = "wallpapercave.com";
    boolean hasDomain = false;
    for (String url : imageUrls) {
      if (url.contains(domain)) {
        hasDomain = true;
        break;
      }
    }
    Assert.assertTrue(hasDomain);
  }

  @Test
  public void testWordCar() {
    String regex = ".*?\\b(?:car|cars)\\b.*?";
    boolean hasMatch = false;
    for (String title : searchTitles) {
      if (title.toLowerCase().matches(regex)) {
        hasMatch = true;
        break;
      }
    }
    Assert.assertTrue(hasMatch);
  }
}
