package web_step;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tw.teddysoft.bdd.web.app.InvoiceWeb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by joe on 2017/5/2.
 */
public class WebSearchCompanyStep implements En {

    @Before
    public void openService() {
        InvoiceWeb.main(new String [0]);
    }

    @After
    public void closeDriver() {
        driver.quit();
    }

    private WebDriver driver = null;



    public WebSearchCompanyStep() {



        Given("^I am on the invoice Web page$", () -> {

            if (null == driver){
                System.setProperty("webdriver.chrome.driver" ,  "./chromedriver.exe");
                driver = new ChromeDriver();
                driver.get("http://localhost:4567/invoice");
            }

        });

        When("^I enter the VAT ID \"([^\"]*)\"$", (String vatID) -> {
            driver.findElement(By.name("vatID")).sendKeys(vatID);
        });

        Then("^I should see the company name \"([^\"]*)\"$", (String companyName) -> {

            driver.findElement(By.name("search")).click();
            assertThat(driver.findElement(By.name("companyName")).getAttribute("value"), is (companyName));
        });

        When("^I enter the company name \"([^\"]*)\"$", (String companyName) -> {

            driver.findElement(By.name("companyName")).sendKeys(companyName);

        });

        Then("^I should see the VAT ID \"([^\"]*)\"$", (String vatID) -> {

            driver.findElement(By.name("search")).click();
            assertThat(driver.findElement(By.name("vatID")).getAttribute("value"), is (vatID));
            driver.quit();
        });
    }
}
