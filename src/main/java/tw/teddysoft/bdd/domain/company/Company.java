package tw.teddysoft.bdd.domain.company;

/**
 * Created by joe on 2017/4/16.
 */
public class Company {

    String vatID = "";
    String companyName = "";

    public Company setVatID (String VatID){
        this.vatID = VatID;
        this.companyName = "";
        return this;
    }

    public Company setCompanyName (String companyName){
        this.companyName = companyName;
        this.vatID = "";
        return this;
    }

    public String getVatID () {

        if(!hasVatID())
            this.vatID = CompanySearcher.getVatID(this.companyName);
        return this.vatID;
    }
    public String getCompanyName () {

        if(!hasCompanyName())
            this.companyName = CompanySearcher.getCompanyName(this.vatID);
        return this.companyName;
    }


    private boolean hasCompanyName(){
        return (this.companyName !="");
    }
    private boolean hasVatID(){
        return (this.vatID !="");
    }
}
