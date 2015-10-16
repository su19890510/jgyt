package com.su.util;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class NetManager {

    public static String HTTP_DOMAIN = "http://120.24.228.100:8088/";

    private NetManager() {}

    private static class NetManagerHolder {
        private static final NetManager NET_MANAGER = new NetManager();
    }

    public static NetManager getInstance() {
        return NetManagerHolder.NET_MANAGER;
    }

    public JSONObject sendHttpRequest(String url, List<NameValuePair> params, HttpMethod httpMethod) {
        url = HTTP_DOMAIN + url;
        Log.v("suzhaohui", "sendHttpRequest");
        String requestUrl;
        if (httpMethod == HttpMethod.GET) {
            Log.v("suzhaohui", "revertUrl begin");
            requestUrl = revertUrl(url, params);
            url = requestUrl;
            Log.v("suzhaohui", "revertUrl end" + requestUrl);
        } else if (httpMethod == HttpMethod.POST) {
            // requestUrl = revertUrl(url, params, );
            // url = requestUrl;
        }
        String jsonData = HttpUtils.getHttpEntity(url, params, httpMethod);

        JSONObject result = null;
        // Log.v("suzhaohui-log",jsonData);
        try {
            if (jsonData != null) {

                result = new JSONObject(jsonData);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    private String revertUrl(String url, List<NameValuePair> params) {
        if ("".equals(url) || params.size() <= 0) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        sb.append(url.contains("?") ? "&" : "?");
        boolean needRemove = false;
        for (NameValuePair pair : params) {
            sb.append(pair.getName()).append("=").append(pair.getValue()).append("&");
            needRemove = true;
        }
        url = needRemove ? sb.substring(0, sb.length() - 1) : sb.toString();
        return url;
    }
}


// HttpPost request = new HttpPost(url);
//// �ȷ�װһ�� JSON ����
// JSONObject param = new JSONObject();
// param.put("name", "rarnu");
// param.put("password", "123456");
//// �󶨵����� Entry
// StringEntity se = new StringEntity(param.toString());
// request.setEntity(se);
//// ��������
// HttpResponse httpResponse = new DefaultHttpClient().execute(request);
//// �õ�Ӧ����ַ�������Ҳ��һ�� JSON ��ʽ���������
// String retSrc = EntityUtils.toString(httpResponse.getEntity());
//// ���� JSON ����
// JSONObject result = new JSONObject( retSrc);
// String token = result.get("token");
