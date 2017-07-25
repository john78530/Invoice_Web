package tw.teddysoft.bdd.domain.invoice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;


/**
 * Created by teddy on 2017/3/2.
 */
public class WhenIssuingInvoiceTest {

    @Test
    public void should_be_a_regular_invoice_when_given_normal_vatRate_and_taxIncludePrice(){
        Invoice invoice = DefaultInvoiceBuilder.newInstance().
                withVatRate(0.05).
                withTaxIncludedPrice(17000).
                issue();

        // DEMO: use junit to perform assertion
        assertNotNull(invoice);
        assertEquals(17000, invoice.getTaxIncludedPrice());
        assertEquals(810, invoice.getVat());
        assertEquals(16190, invoice.getTaxExcludedPrice());
        assertEquals(0.05, invoice.getVatRate(), 0.001);

        // DEMO: use AssertJ to perform assertion
        assertThat(invoice).isNotNull();
        assertThat(invoice.getTaxIncludedPrice()).isEqualTo(17000);
        assertThat(invoice.getVat()).isEqualTo(810);
        assertThat(invoice.getTaxExcludedPrice()).isEqualTo(16190);
        assertThat(invoice.getVatRate()).isEqualTo(0.05);
    }


    @Test
    public void should_be_initially_an_empty_invoice(){
        Invoice invoice = DefaultInvoiceBuilder.newInstance().issue();
        test_that_an_invoice_has_all_zero_value(invoice);
    }

    @Test
    public void should_be_an_empty_invoice_when_given_taxIncludedPrice_is_zero(){
        Invoice invoice = DefaultInvoiceBuilder.newInstance().
                withVatRate(0).
                withTaxIncludedPrice(0).
                issue();
        test_that_an_invoice_has_all_zero_value(invoice);

        invoice = DefaultInvoiceBuilder.newInstance().
                withTaxIncludedPrice(0).
                issue();
        test_that_an_invoice_has_all_zero_value(invoice);
    }

    @Test
    public void should_be_a_regular_invoice_when_given_normal_vatRate_and_taxExcludePrice(){
        Invoice invoice = DefaultInvoiceBuilder.newInstance().
                withVatRate(0.05).
                withTaxExcludedPrice(34286).
                issue();

        // use AssertJ to perform assertion
        assertThat(invoice).isNotNull();
        assertThat(invoice.getTaxIncludedPrice()).isEqualTo(36000);
        assertThat(invoice.getVat()).isEqualTo(1714);
        assertThat(invoice.getTaxExcludedPrice()).isEqualTo(34286);
        assertThat(invoice.getVatRate()).isEqualTo(0.05);
    }

    @Test
    public void should_use_the_latest_assigned_value_to_calc_invoice(){
        Invoice invoice = DefaultInvoiceBuilder.newInstance().
                withVatRate(0.05).
                withTaxIncludedPrice(10).
                withTaxExcludedPrice(10).
                issue();

        assertThat(invoice).isNotNull();
        assertThat(invoice.getTaxIncludedPrice()).isEqualTo(11);
        assertThat(invoice.getVat()).isEqualTo(1);
        assertThat(invoice.getTaxExcludedPrice()).isEqualTo(10);
        assertThat(invoice.getVatRate()).isEqualTo(0.05);
    }


    // DEMO: use hamcrest to perform assertion
    private void test_that_an_invoice_has_all_zero_value(Invoice invoice){
        assertNotNull(invoice);
        org.hamcrest.MatcherAssert.assertThat(invoice.getTaxIncludedPrice(), is(0));
        org.hamcrest.MatcherAssert.assertThat(invoice.getVat(), is (0));
        org.hamcrest.MatcherAssert.assertThat(invoice.getTaxExcludedPrice(), is(0));
        org.hamcrest.MatcherAssert.assertThat(invoice.getVatRate(), is (0.0));
    }
}
