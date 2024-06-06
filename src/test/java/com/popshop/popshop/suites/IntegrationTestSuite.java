package com.popshop.popshop.suites;

import com.popshop.popshop.IntegrationTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        IntegrationTests.class
})
public class IntegrationTestSuite {
}