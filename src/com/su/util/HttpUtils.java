package com.su.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public final class HttpUtils {

    public static final String HTTP_TRANSFER_ERROR = "connection failure";

    private HttpUtils() {}

    public static String getHttpEntity(String url, List<NameValuePair> params, HttpMethod method) {
        switch (method) {
            case GET:
                return HttpUtils.sendGet(url);
            case POST:
                return HttpUtils.httpPost(url, params);
            default:
                return HttpUtils.httpPost(url, params);
        }
    }

    private static DefaultHttpClient getHttpClient() {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 6000);
        HttpConnectionParams.setSoTimeout(httpParams, 6000);
        ConnManagerParams.setTimeout(httpParams, 6000);
        DefaultHttpClient mHttpClient = new DefaultHttpClient(httpParams);
        mHttpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        mHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
        mHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);
        
        return mHttpClient;
    }
    
    private static String sendGet(String url) {
        DefaultHttpClient mHttpClient = getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        Log.v("API SendGet", url);
        try {
            HttpResponse httpResponse = mHttpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status == HttpStatus.SC_OK) {
                    String response = EntityUtils.toString(httpEntity, "UTF-8");
                    return response;
                }
            }
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return "{error:timeOut}";
        } catch (HttpHostConnectException e) {
            e.printStackTrace();
            return "{error:netError}";
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    
    private static String sendPost(String url, List<NameValuePair> pairs) {
//        try {
//            JSONObject jsonObj = new JSONObject();
//            for (NameValuePair nameValuePair : pairs) {
//                jsonObj.put(nameValuePair.getName(), nameValuePair.getValue());
//            }
//            HttpPost httpPost = new HttpPost(url);
//            StringEntity entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);
//            entity.setContentType("application/json");
//            httpPost.setEntity(entity);
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
//            httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            httpPost.setHeader("Cache-Control", "max-age=0");
//            httpPost.setHeader("Connection", "keep-alive");
//            httpPost.setHeader("domain", "120.24.228.100");
//            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
//            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:28.0) Gecko/20100101 Firefox/28.0");
//            DefaultHttpClient client = getHttpClient();
//            HttpResponse response = client.execute(httpPost);
//            HttpEntity httpEntity = response.getEntity();
//            if (httpEntity != null) {
//                int status = response.getStatusLine().getStatusCode();
//                if (status == HttpStatus.SC_OK) {
//                    return EntityUtils.toString(httpEntity, "UTF-8");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
        try {
            UrlEncodedFormEntity requestHttpEntity = new UrlEncodedFormEntity(pairs, "UTF-8");
            requestHttpEntity.setContentEncoding(HTTP.UTF_8);
            requestHttpEntity.setContentType("application/json");
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(requestHttpEntity);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Accept", "application/json,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpPost.setHeader("Cache-Control", "max-age=0");
            httpPost.setHeader("Connection", "keep-alive");
            httpPost.setHeader("domain", "120.24.228.100");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:28.0) Gecko/20100101 Firefox/28.0");
            DefaultHttpClient mHttpClient = getHttpClient();
            HttpResponse response = mHttpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                int status = response.getStatusLine().getStatusCode();
                if (status == HttpStatus.SC_OK) {
                    return EntityUtils.toString(httpEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            Log.v("API Error", "Send API Request[" + url + "] Error.", e);
        }
        return null;
    }

    static String httpPost(String url, List<NameValuePair> pairs) {
        try {
            URL _url = new URL(url);
            String data = revertUrl(pairs);
            Log.v("API", "Http Post " + url + " --data " + data);
            HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            OutputStreamWriter request = new OutputStreamWriter(connection.getOutputStream());
            request.write(data);
            request.flush();
            request.close();
            String line = "";
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrConnectionTypeName(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
        String netInfo = "";
        String type = (networkInfo == null ? null : networkInfo.getTypeName());
        if ("".equals(type) || type == null) {
            netInfo += type;
        }
        String extraInfo = networkInfo.getExtraInfo();
        if ("".equals(extraInfo) || extraInfo == null) {
            netInfo += " " + extraInfo;
        }
        return netInfo;
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
        if (null == networkInfo) {
            return false;
        }
        boolean connected = networkInfo.isConnected();
        return connected;
    }

    public static String getConnectionInfo(Context context) {
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        return networkInfo.getReason();
        // NetworkInfo 有一下方�?
        // getDetailedState()：获取详细状态�??
        // getExtraInfo()：获取附加信息�??
        // getReason()：获取连接失败的原因�?
        // getType()：获取网络类�?(�?般为移动或Wi-Fi)�?
        // getTypeName()：获取网络类型名�?(�?般取值�?�WIFI”或“MOBILE�?)�?
        // isAvailable()：判断该网络是否可用�?
        // isConnected()：判断是否已经连接�??
        // isConnectedOrConnecting()：判断是否已经连接或正在连接�?
        // isFailover()：判断是否连接失败�??
        // isRoaming()：判断是否漫�?
        //
        // 当用wifi上的时�??
        // getType �? WIFI
        // getExtraInfo是空�?
        // 当用手机上的时�??
        // getType 是MOBILE
        //
        // 用移动CMNET方式
        // getExtraInfo 的�?�是cmnet
        // 用移动CMWAP方式
        // getExtraInfo 的�?�是cmwap 但是不在代理的情况下访问普�?�的网站访问不了
        //
        // 用联�?3gwap方式
        // getExtraInfo 的�?�是3gwap
        // 用联�?3gnet方式
        // getExtraInfo 的�?�是3gnet
        // 用联通uniwap方式
        // getExtraInfo 的�?�是uniwap
        // 用联通uninet方式
        // getExtraInfo 的�?�是uninet
    }
    
    private static String revertUrl(List<NameValuePair> params) {
        String url = "";
        StringBuilder sb = new StringBuilder("");
        boolean needRemove = false;
        for (NameValuePair pair : params) {
            sb.append(pair.getName()).append("=").append(encode(pair.getValue())).append("&");
            needRemove = true;
        }
        url = needRemove ? sb.substring(0, sb.length() - 1) : sb.toString();
        return url;
    }
    
    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
        }
        return value;
    }
}