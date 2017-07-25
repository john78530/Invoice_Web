package tw.teddysoft.bdd.domain.invoice.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by teddy on 2017/3/26.
 */
public class Utility {

    public static final String CUCUMBER_PROPERTY_FILE = "./src/test/resources/cucumber.properties";

    public static boolean isUnderInvoiceWebMode(){
        Properties prop = Utility.loadProperties(Utility.CUCUMBER_PROPERTY_FILE);
        if("web".equals(prop.getProperty("invoice.ui"))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isUnderCompanyWebMode() {

        Properties prop = Utility.loadProperties(Utility.CUCUMBER_PROPERTY_FILE);
        if("web".equals(prop.getProperty("company.ui"))) {
            return true;
        } else {
            return false;
        }
    }

    public static Properties loadProperties(String fileName)  {
        InputStream input = null;
        Properties prop = new Properties();

        try {
            input = new FileInputStream(fileName);
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(null != input)
                try {
                    input.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
        return prop;
    }

    public static void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
