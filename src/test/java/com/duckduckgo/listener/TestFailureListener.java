package com.duckduckgo.listener;

import com.duckduckgo.config.Configuration;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestFailureListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    System.out.println("Test Case Failed");

    try {
      Configuration.takeScreenshot(result.getName());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
