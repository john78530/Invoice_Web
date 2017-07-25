package tw.teddysoft.bdd.domain.invoice;

/**
 * Created by teddy on 2017/3/30.
 */
public interface InvoiceBuilder {

    public static InvoiceBuilder newInstance(){
        return new DefaultInvoiceBuilder();
    }

    public InvoiceBuilder withVatRate(double vatRate);

    public InvoiceBuilder withTaxIncludedPrice(int taxIncludedPrice);

    public Invoice issue();

    public InvoiceBuilder withTaxExcludedPrice(Integer taxExcludedPrice);

}
