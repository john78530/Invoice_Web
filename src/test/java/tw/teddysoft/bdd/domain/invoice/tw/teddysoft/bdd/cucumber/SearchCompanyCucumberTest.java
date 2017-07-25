package tw.teddysoft.bdd.domain.invoice.tw.teddysoft.bdd.cucumber;

/**
 * Created by joe on 2017/4/17.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features/entering_vatid_and_company_name.feature",
        glue = {"step"},
        format = {"json:target/entering_vatid_and_company_name/cucumber.json", "html:target/site/entering_vatid_and_company_name/cucumber-pretty"}
)
public class SearchCompanyCucumberTest {
}
