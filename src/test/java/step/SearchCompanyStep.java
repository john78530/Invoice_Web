package step;
import cucumber.api.java8.En;
import tw.teddysoft.bdd.domain.company.Company;



import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by joe on 2017/4/14.
 */
public class SearchCompanyStep implements En {


//    String result;

    Company company;

    public SearchCompanyStep() {

        When("^I enter the VAT ID \"([^\"]*)\"$", (String vatID) -> {

            company = new Company().setVatID(vatID);

        });

        Then("^I should see the company name \"([^\"]*)\"$", (String companyName) -> {

            assertThat(company.getCompanyName(), is (companyName));

        });

        When("^I enter the company name \"([^\"]*)\"$", (String companyName) -> {

            company = new Company().setCompanyName(companyName);
        });

        Then("^I should see the VAT ID \"([^\"]*)\"$", (String vatID) -> {

            assertThat(company.getVatID(), is (vatID));

        });
    }
}
