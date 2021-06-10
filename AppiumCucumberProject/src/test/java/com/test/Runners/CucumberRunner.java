package com.test.Runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "html:target/cucumberHtmlReport"},
        features = "src/test/resources/features/",
        glue = {"com.test"}
)
public class CucumberRunner {

}

//public class CucumberTestRunner extends BaseTest {
//    private TestNGCucumberRunner testNGCucumberRunner;
//    @BeforeClass(alwaysRun = true)
//    public void setUpClass() {
//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//    }
//    @Test(groups = "cucumber", description = "Run Cucumber Features.", dataProvider = "scenarios")
//    public void scenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
//        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
//    }
//    @DataProvider
//    public Object[][] scenarios() {
//        return testNGCucumberRunner.provideScenarios();
//    }
//    @AfterClass(alwaysRun = true)
//    public void tearDownClass() {
//        testNGCucumberRunner.finish();
//    }
//}