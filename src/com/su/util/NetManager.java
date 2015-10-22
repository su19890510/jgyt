package com.su.util;

import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class NetManager {

    private static final String EQ = "=";
    private static final String AND = "&";
    private static final String Q = "?";
    
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
        String responseJson = null;
        switch (httpMethod) {
            case GET:
                url = revertUrl(url, params);
                responseJson = HttpUtils.sendRequert(url, params, httpMethod);
                break;
            case POST:
                responseJson = HttpUtils.sendRequert(url, params, httpMethod);
                break;
            default:
                break;
        }
        Log.v("API Response", url + " >> " + responseJson);
        JSONObject result = null;
        try {
            if (responseJson != null) {
                result = new JSONObject(responseJson);
            }
        } catch (JSONException e) {
            Log.e("API Json", e.getLocalizedMessage(), e);
        }
        return result;
    }

    private String revertUrl(String url, List<NameValuePair> params) {
        if ("".equals(url) || params.size() <= 0) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        sb.append(url.contains(Q) ? AND : Q);
        boolean needRemove = false;
        for (NameValuePair pair : params) {
            sb.append(pair.getName()).append(EQ).append(pair.getValue()).append(AND);
            needRemove = true;
        }
        url = needRemove ? sb.substring(0, sb.length() - 1) : sb.toString();
        return url;
    }
}
