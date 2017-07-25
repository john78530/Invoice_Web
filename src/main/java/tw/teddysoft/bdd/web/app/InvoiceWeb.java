package tw.teddysoft.bdd.web.app;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import tw.teddysoft.bdd.domain.company.Company;
import tw.teddysoft.bdd.domain.invoice.*;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


/**
 * Created by teddy on 2017/3/2.
 */

/**
 * VelocityTemplateRoute example.
 */
public final class InvoiceWeb {

    public static void main(String[] args) {

        get("/invoice", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("Title", "三聯式發票");

            return new ModelAndView(model, "invoice_input.vm"); // located in the resources directory
        }, new VelocityTemplateEngine());


        post("/invoice", (request, response) -> {
            Invoice invoice;
            int taxIncludedPrice = integerValue(request.queryMap("taxIncludedPrice").value());
            int taxExcludedPrice = integerValue(request.queryMap("taxExcludedPrice").value());
            double vatRate = request.queryMap("vatRate").doubleValue();

            if (isUseTaxIncludedPriceToCalculateInvoice(taxIncludedPrice)) {
                invoice = InvoiceBuilder.newInstance().
                        withTaxIncludedPrice(taxIncludedPrice).
                        withVatRate(vatRate).
                        issue();
            }
            else {
                invoice = InvoiceBuilder.newInstance().
                        withTaxExcludedPrice(taxExcludedPrice).
                        withVatRate(vatRate).
                        issue();
            }

            Map<String, Object> model = new HashMap<>();

            model.put("invoice", invoice);
            model.put("companyName", "");
            model.put("vatID", "");
            return new ModelAndView(model, "invoice_result.vm"); // located in the resources directory
        }, new VelocityTemplateEngine());

        post("/invoice/company",(request, response) ->{

            Company company;
            String _companyName = request.queryMap("companyName").value();
            String _vatID = request.queryMap("vatID").value();

            if (isUseCompanyNameToSearch(_companyName)) {
                company = new Company().setCompanyName(_companyName);
            }
            else{
                company = new Company().setVatID(_vatID);
            }
//            System.out.println("companyName = " + company.getCompanyName() );
//            System.out.println("vatID = " + company.getVatID());
            String companyName = company.getCompanyName();
            String vatID = company.getVatID();
            Map<String, Object> model = new HashMap<>();
            model.put("companyName", companyName);
            model.put("vatID", vatID);


            Invoice invoice = InvoiceBuilder.newInstance().issue();
            model.put("invoice", invoice);

            return new ModelAndView(model, "invoice_result.vm"); // located in the resources directory
        },new VelocityTemplateEngine());
    }

    private static boolean isUseCompanyNameToSearch(String CompanyName){
        return !(CompanyName.isEmpty());
    }

    private static boolean isUseTaxIncludedPriceToCalculateInvoice(int taxIncludedPrice){
        return !(0 == taxIncludedPrice);
    }

    private static int integerValue(String str){
        if ((null == str) || ("".equals(str)))
            return 0;
        else
           return Integer.valueOf(str);
    }


}