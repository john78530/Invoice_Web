package tw.teddysoft.bdd.domain.invoice;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by teddy on 2017/3/16.
 */
public class InvoiceCalculatorTest {
    private final double VAT_RATE = 0.05;

    @Test
    public void vat_should_be_zero_when_tax_include_price_is_0_and_vat_rat_is_5_percent(){
        assertThat(InvoiceCalculator.getVAT(0,VAT_RATE)).isEqualTo(0);
    }

    @Test
    public void vat_should_be_zero_when_tax_include_price_is_10_and_vat_rat_is_5_percent(){
        assertThat(InvoiceCalculator.getVAT(10,VAT_RATE)).isEqualTo(0);
    }

    @Test
    public void vat_should_be_1_when_tax_include_price_is_11_and_vat_rat_is_5_percent(){
        assertThat(InvoiceCalculator.getVAT(11,VAT_RATE)).isEqualTo(1);
    }

    @Test
    public void tax_exclude_price_should_be_10_when_tax_include_price_is_10_and_vat_rat_is_5_percent(){
        assertThat(InvoiceCalculator.getTaxExcludedPrice(10,VAT_RATE)).isEqualTo(10);
    }

    @Test
    public void tax_exclude_price_should_be_10_when_tax_include_price_is_11_and_vat_rat_is_5_percent(){
        assertThat(InvoiceCalculator.getTaxExcludedPrice(10,VAT_RATE)).isEqualTo(10);
    }
}
