package com.baidu;

import java.util.Arrays;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestRunner {
    public static void main(String[] args) {
        // Create a TestNG instance
        TestNG testNG = new TestNG();
        // Create a TestListenerAdapter instance for result logging
        TestListenerAdapter tla = new TestListenerAdapter();

        // Specify the path to your testng.xml file
        String xmlSuiteFile = "D:\\VSCodeProject\\BaiduSearchImage\\searchbyimage\\testng.xml"; 

        // Set the testng.xml file to be executed
        testNG.setTestSuites(Arrays.asList(xmlSuiteFile));

        // Add the TestListenerAdapter to the TestNG instance
        testNG.addListener(tla);

        // Run the tests
        testNG.run();
    }
}
