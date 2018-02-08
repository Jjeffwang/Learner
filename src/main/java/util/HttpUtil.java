package util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${WangChengYong} on 2018/2/8.
 */
public class HttpUtil {


    private static String doPost(String url, List<NameValuePair> params, String strParams) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            if (params == null || params.size() == 0) {
                httpPost.setEntity(new StringEntity(strParams, "UTF-8"));
            } else {
                httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            }
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            HttpResponse httpResponse = httpclient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            return new String(EntityUtils.toString(httpEntity).getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return "调用服务异常";
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        String url = "http://127.0.0.1:8088/tzpay/getLogger";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("orderNo",
//                "123"));
        try {
            String getOrderDetails = doPost(url, params, "123");
            System.out.println(getOrderDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
