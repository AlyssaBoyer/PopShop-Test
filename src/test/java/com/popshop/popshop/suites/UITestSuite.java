package com.popshop.popshop.suites;

import com.popshop.popshop.UITests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        UITests.class
})
public class UITestSuite {
}