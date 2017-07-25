package tw.teddysoft.bdd.domain.invoice;

/**
 * Created by teddy on 2017/3/9.
 */
public class DefaultInvoiceBuilder implements InvoiceBuilder {

    private double vatRate = 0.0;
    private int taxIncludedPrice = 0;
    private  int taxExcludedPrice=0;

    public DefaultInvoiceBuilder() {};

    public static DefaultInvoiceBuilder newInstance(){
        return new DefaultInvoiceBuilder();
    }

    @Override
    public DefaultInvoiceBuilder withVatRate(double vatRate) {
        this.vatRate = vatRate;
        return this;
    }

    @Override
    public DefaultInvoiceBuilder withTaxIncludedPrice(int taxIncludedPrice) {
        this.taxIncludedPrice = taxIncludedPrice;
        this.taxExcludedPrice = 0;
        return this;
    }

    @Override
    public Invoice issue() {
        if(isUseTaxExcludedPriceToCalculateInvoice()) {
            taxIncludedPrice =(int) Math.round(taxExcludedPrice * (1 + vatRate));
        }

        return new Invoice(taxIncludedPrice, vatRate,
                InvoiceCalculator.getTaxExcludedPrice(taxIncludedPrice, vatRate)
                , InvoiceCalculator.getVAT(taxIncludedPrice, vatRate));
    }

    @Override
    public DefaultInvoiceBuilder withTaxExcludedPrice(Integer taxExcludedPrice) {
        this.taxExcludedPrice = taxExcludedPrice;
        taxIncludedPrice = 0;
        return this;
    }

    private boolean isUseTaxExcludedPriceToCalculateInvoice(){
        return (0 == taxIncludedPrice && 0 != taxExcludedPrice);
    }
}
