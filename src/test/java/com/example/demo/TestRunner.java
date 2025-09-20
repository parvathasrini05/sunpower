java
package com.example.demo;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Complete Backend Test Suite")
@SelectPackages({
    "com.example.demo.models",
    "com.example.demo.repositories",
    "com.example.demo.services",
    "com.example.demo.controllers"
})
public class TestRunner {
    // This class will run all tests in the specified packages
}
