package tw.teddysoft.bdd.domain.invoice;

import org.junit.Test;
import tw.teddysoft.bdd.domain.company.Company;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by joe on 2017/4/16.
 */
public class WhenSearchCompanyTest {

    @Test
    public void search_By_companyName() throws IOException {

        Company company = new Company()
                .setCompanyName("泰迪軟體科技有限公司");


        assertThat(company).isNotNull();
        assertThat(company.getVatID()).isEqualTo("53909614");
        assertThat(company.getCompanyName()).isEqualTo("泰迪軟體科技有限公司");

    }

    @Test
    public void search_By_vatID() throws IOException {

        Company company = new Company()
                .setVatID("53909614");

        assertThat(company).isNotNull();
        assertThat(company.getVatID()).isEqualTo("53909614");
        assertThat(company.getCompanyName()).isEqualTo("泰迪軟體科技有限公司");

    }

    @Test
    public void search_By_Last_type_CompanyName() throws IOException {

        Company company = new Company()
                .setVatID("123456")
                .setCompanyName("泰迪軟體科技有限公司");

        assertThat(company).isNotNull();
        assertThat(company.getVatID()).isEqualTo("53909614");
        assertThat(company.getCompanyName()).isEqualTo("泰迪軟體科技有限公司");

    }

    @Test
    public void search_By_Last_type_vatID() throws IOException {

        Company company = new Company()
                .setCompanyName("泰迪軟體科技有限公司")
                .setVatID("53909614");

        assertThat(company).isNotNull();
        assertThat(company.getVatID()).isEqualTo("53909614");
        assertThat(company.getCompanyName()).isEqualTo("泰迪軟體科技有限公司");

    }
}
