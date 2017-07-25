package tw.teddysoft.bdd.domain.invoice.tw.teddysoft.bdd.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by joe on 2017/5/3.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features/entering_vatid_and_company_name_web.feature",
        glue = {"web_step"},
        format = {"json:target/entering_vatid_and_company_name_web/cucumber.json", "html:target/site/entering_vatid_and_company_name/cucumber-pretty"}
)
public class WebSearchCompanyCucumberTest {
}
