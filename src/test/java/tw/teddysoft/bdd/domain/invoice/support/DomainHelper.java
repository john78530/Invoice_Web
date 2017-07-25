package tw.teddysoft.bdd.domain.invoice.support;

import tw.teddysoft.bdd.domain.invoice.Invoice;
import tw.teddysoft.bdd.domain.invoice.InvoiceBuilder;

/**
 * Created by teddy on 2017/3/30.
 */
public class DomainHelper {

    InvoiceBuilder builder;
    Invoice invoice;

    public DomainHelper(InvoiceBuilder builder){
        this.builder = builder;
    }

    public InvoiceBuilder getInvoiceBuilder() {
        return builder;
    }

    public void setMyInvoice(Invoice issued) {
        invoice = issued;
    }

    public Invoice getMyInvoice() {
        return invoice;
    }
}
