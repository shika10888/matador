package com.duckduckgo.config;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

  private static Properties prop;
  private static WebDriver driver;

  // Setting up the browser from config.properties file ,defining the path of chromedriver & fetching the url of duckduckgo search engine.
  private static void initializeDriver() {
    prop.getProperty("browser");
    System.setProperty(
      "webdriver.chrome.driver",
      "chromedriver-win64/chromedriver.exe"
    );
    driver = new ChromeDriver();
    driver.get(prop.getProperty("duckduckgo.url"));
    driver.manage().window().maximize();
  }

  private static void initializeProperties() {
    prop = new Properties();
    try {
      prop.load(Configuration.class.getResourceAsStream("/config.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Properties getProperties() {
    if (prop == null) {
      initializeProperties();
    }
    return prop;
  }

  public static WebDriver getDriver() {
    if (driver == null) {
      initializeDriver();
    }
    return driver;
  }

  public static void takeScreenshot(String testMethodName) throws IOException {
    File srcfile =
      ((TakesScreenshot) Configuration.getDriver()).getScreenshotAs(
          OutputType.FILE
        );
    FileUtils.copyFile(
      srcfile,
      new File("./screenshots/" + testMethodName + "" + ".jpg")
    );
  }
}
