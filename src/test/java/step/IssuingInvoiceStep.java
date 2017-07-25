package step;

import cucumber.api.java8.En;
import tw.teddysoft.bdd.domain.invoice.support.DomainHelper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by teddy on 2017/3/2.
 */
public class IssuingInvoiceStep implements En {
    DomainHelper helper;

    public IssuingInvoiceStep(DomainHelper helper) {
        this.helper = helper;

        Given("^Ｔhe VAT rate is (\\d+\\.\\d+)$", (Double vatRate) -> {
            // TODO
            helper.getInvoiceBuilder().withVatRate(vatRate);

        });

        Given("^the tax included price provided is (\\d+)$", (Integer taxIncludedPrice) -> {
            helper.getInvoiceBuilder().withTaxIncludedPrice(taxIncludedPrice);
        });

        Given("^the tax excluded price provided is (\\d+)$", (Integer taxExcludedPrice) -> {
            helper.getInvoiceBuilder().withTaxExcludedPrice(taxExcludedPrice);

        });

        Given("^I am on the invoice Web page$", () -> {
        });

        When("^I issue a company invoice$", () -> {
            helper.setMyInvoice(helper.getInvoiceBuilder().issue());
        });

        Then("^I should see the VAT is (\\d+)$", (Integer vat) -> {
            assertThat(helper.getMyInvoice().getVat(), is (vat));
        });

        Then("^the tax excluded price is (\\d+)$", (Integer taxExcludedPrice) -> {
            assertThat(helper.getMyInvoice().getTaxExcludedPrice(), is (taxExcludedPrice));
        });

        Then("^the tax included price is (\\d+)$", (Integer taxIncludedPrice) -> {
            assertThat(helper.getMyInvoice().getTaxIncludedPrice(), is (taxIncludedPrice));
        });
    }
}
