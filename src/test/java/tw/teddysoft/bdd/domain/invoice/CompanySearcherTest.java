package tw.teddysoft.bdd.domain.invoice;

import org.junit.Test;
import tw.teddysoft.bdd.domain.company.CompanySearcher;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by joe on 2017/4/16.
 */
public class CompanySearcherTest {

    @Test
    public void getVatId_By_CompanyName() throws IOException {
        assertThat(CompanySearcher.getVatID("泰迪軟體科技有限公司")).isEqualTo("53909614");
    }

    @Test
    public void getCompanyName_By_vatID() throws IOException {
        assertThat(CompanySearcher.getCompanyName("53909614")).isEqualTo("泰迪軟體科技有限公司");
    }

    @Test
    public void enter_NotExist_Company() throws IOException {
        assertThat(CompanySearcher.getVatID("王小明有限公司")).isEqualTo("查無此統一編號");
    }

    @Test
    public void enter_NotExist_VatID() throws IOException {
        assertThat(CompanySearcher.getCompanyName("12345678")).isEqualTo("查無此公司");
    }

    @Test
    public void enter_ErrorVatId_Format() throws IOException {
        assertThat(CompanySearcher.getCompanyName("1234567")).isEqualTo("統一編號格式錯誤");
    }

    @Test
    public void enter_商業名稱_Format_vatID() throws IOException {
        assertThat(CompanySearcher.getCompanyName("11111111")).isEqualTo("東發視聽社");
    }

    @Test
    public void enter_名稱_Format_vatID() throws IOException {
        assertThat(CompanySearcher.getCompanyName("92021164")).isEqualTo("國立臺北科技大學");
    }

    @Test
    public void enter_外商_Format_vatID() throws IOException {
        assertThat(CompanySearcher.getCompanyName("28988153")).isEqualTo("英屬蓋曼群島商科嘉國際股份有限公司");
    }




}
