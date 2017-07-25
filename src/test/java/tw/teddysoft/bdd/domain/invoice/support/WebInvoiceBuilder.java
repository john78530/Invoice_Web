package tw.teddysoft.bdd.domain.invoice.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tw.teddysoft.bdd.domain.invoice.Invoice;
import tw.teddysoft.bdd.domain.invoice.InvoiceBuilder;

/**
 * Created by teddy on 2017/3/30.
 */
public class WebInvoiceBuilder implements InvoiceBuilder {
    private WebDriver driver = null;

    public WebInvoiceBuilder(){
    }

    private WebDriver getWebDriver(){
        if (null == driver){
            System.setProperty("webdriver.chrome.driver" ,  "./chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("http://localhost:4567/invoice");
        }
        return  driver;
    }

    @Override
    public InvoiceBuilder withVatRate(double vatRate) {
        return this;
    }

    @Override
    public InvoiceBuilder withTaxIncludedPrice(int taxIncludedPrice) {
        getWebDriver().findElement(By.name("taxIncludedPrice")).sendKeys(String.valueOf(taxIncludedPrice));
        return this;
    }


    @Override
    public InvoiceBuilder withTaxExcludedPrice(Integer taxExcludedPrice) {
        getWebDriver().findElement(By.name("taxExcludedPrice")).sendKeys(String.valueOf(taxExcludedPrice));
        return this;
    }

    @Override
    public Invoice issue() {
        try{
            getWebDriver().findElement(By.name("issue")).click();
            int vat = Integer.valueOf(getWebDriver().findElement(By.name("vat")).getAttribute("value"));
            int taxExcludedPrice = Integer.valueOf(getWebDriver().findElement(By.name("taxExcludedPrice")).getAttribute("value"));
            int taxIncludedPrice = Integer.valueOf(getWebDriver().findElement(By.name("taxIncludedPrice")).getAttribute("value"));
            double vatRate = 0.05;
            return new Invoice(taxIncludedPrice, vatRate, taxExcludedPrice, vat);
        }
        finally {
            getWebDriver().quit();
        }
    }

}
