package tw.teddysoft.bdd.domain.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.URL;

import java.nio.charset.Charset;

/**
 * Created by joe on 2017/4/14.
 */
public class CompanySearcher {

    static Logger logger = LoggerFactory.getLogger(CompanySearcher.class);

    //http://company.g0v.ronny.tw/api/show/統一編號
    //http://company.g0v.ronny.tw/api/search?q=公司名稱
    public static String getCompanyName(String vatID) {


        if(vatID.length() != 8)
            return "統一編號格式錯誤";

        try {

            String url = "http://company.g0v.ronny.tw/api/show/" + vatID;
            JSONObject json = readJsonFromUrl( url);
            return companyNameParser(json);

        } catch (IOException e) {

            return "網路異常";
        }

    }

    public static String getVatID(String companyName) {

        try {

            String decode_company = new String(companyName.getBytes("UTF-8"), "UTF-8");
            String url = "http://company.g0v.ronny.tw/api/search?q=" + decode_company;
            JSONObject json = readJsonFromUrl( url);
            return vatIdParser(json);

        } catch (IOException e) {

            return "網路異常";
        }

    }

    private static String  companyNameParser(JSONObject json){

        String result = "";

        if(json.has("error") ) {
            result = "查無此公司";
        }
        else if(json.getJSONObject("data").has("公司名稱")){
            Object obj = json.getJSONObject("data").get("公司名稱");
            if(  obj instanceof JSONArray){
                result = json.getJSONObject("data").getJSONArray("公司名稱").getString(0);
            }
            else {
                result = json.getJSONObject("data").getString("公司名稱");
            }
        }
        else if(json.getJSONObject("data").has("名稱")){
            result = json.getJSONObject("data").getString("名稱");
        }
        else if(json.getJSONObject("data").has("商業名稱")){
            result = json.getJSONObject("data").getString("商業名稱");
        }
        return result;
    }

    private static String vatIdParser(JSONObject json){

        String result="";
        if( Integer.parseInt(json.get("found").toString()) == 0){
            result = "查無此統一編號";
        }
        else if (json.getJSONArray("data").getJSONObject(0).has("統一編號")) {

            result = json.getJSONArray("data").getJSONObject(0).getString("統一編號");
        }

        return result;

    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

}
