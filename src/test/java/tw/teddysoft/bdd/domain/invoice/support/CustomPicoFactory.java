package tw.teddysoft.bdd.domain.invoice.support;

import cucumber.runtime.java.picocontainer.PicoFactory;
import tw.teddysoft.bdd.domain.invoice.DefaultInvoiceBuilder;

import java.util.Properties;

/**
 * Created by teddy on 2017/3/19.
 */
public class CustomPicoFactory extends PicoFactory  {

    public CustomPicoFactory()  {
        Properties prop = Utility.loadProperties(Utility.CUCUMBER_PROPERTY_FILE);
        if(Utility.isUnderInvoiceWebMode()) {
            addClass(WebInvoiceBuilder.class);
        } else {
            addClass(DefaultInvoiceBuilder.class);
        }
    }
}
