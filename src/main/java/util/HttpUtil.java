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

        String url = "http://127.0.0.1:8088/proxy/handle";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("orderNo",
//                "123"));
        String param="[{\n" +
                " \"@type\": \"cn.com.bsfit.frms.obj.AuditObject\",\n" +
                " \t\"frms_order_id\": \"63005288\",\n" +
                " \"frms_user_name\": \"邦盛科技\",\n" +
                " \"frms_id_no\": \"352225196903021521\",\n" +
                " \"frms_phone_no\": \"18388233116\",\n" +
                " \"frms_trans_time\": 1532931221000,\n" +
                " \"frms_user_id\": \"1234567825\",\n" +
                " \"frms_biz_code\": \"PAY.BUY\",\n" +
                " \"frms_finger_print\": \"0kdj-121k-jkks-32kd\",\n" +
                " \"frms_pay_type\": \"QYJ_PAY\",\n" +
                "        \"frms_suppcategory_id\":\"5\",\n" +
                "        \"frms_suppgoods_id\":\"20180730\",\n" +
                "        \"frms_goods_amount\":3,\n" +
                "        \"frms_goods_order\":\"03213255\",\n" +
                " \"frms_uuid\": \"01ac5ef4-95af-4914-b065-913a2720a0df\",\n" +
                " \"frms_ip_addr\": \"192.168.19.128\",\n" +
                " \"frms_goods_id\":\"5\",\n" +
                " \"frms_goods_vol\":5L\n" +
                " \n" +
                " \n" +
                "},\n" +
                "\n" +
                "{\n" +
                " \"@type\": \"cn.com.bsfit.frms.obj.AuditObject\",\n" +
                " \"frms_user_name\": \"邦盛科技\",\n" +
                " \t\"frms_order_id\": \"63005288\",\n" +
                " \"frms_id_no\": \"352225196903021521\",\n" +
                " \"frms_phone_no\": \"18388233116\",\n" +
                " \"frms_trans_time\": 1532931221000,\n" +
                " \"frms_user_id\": \"1234567825\",\n" +
                " \"frms_biz_code\": \"PAY.BUY\",\n" +
                " \"frms_finger_print\": \"0kdj-121k-jkks-32kd\",\n" +
                " \"frms_pay_type\": \"QYJ_PAY\",\n" +
                "        \"frms_suppcategory_id\":\"5\",\n" +
                "        \"frms_suppgoods_id\":\"20180730\",\n" +
                "        \"frms_goods_amount\":\"3\",\n" +
                "        \"frms_goods_order\":\"03213254\",\n" +
                " \"frms_uuid\": \"01ac5ef4-95af-4914-b065-913a2720a0df\",\n" +
                " \"frms_ip_addr\": \"192.168.19.128\",\n" +
                " \"frms_goods_id\":\"5\",\n" +
                " \"frms_goods_vol\":5L\n" +
                "}\n" +
                "\n" +
                "]";


        try {
            String getOrderDetails = doPost(url, params, param);
            System.out.println(getOrderDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
