package com.popshop.popshop.suites;

import com.popshop.popshop.controllers.FigureControllerTest;
import com.popshop.popshop.models.PopFigureTest;
import com.popshop.popshop.services.FigureServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        FigureControllerTest.class,
        FigureServiceTest.class,
        PopFigureTest.class
})
public class UnitTestSuite {
}
