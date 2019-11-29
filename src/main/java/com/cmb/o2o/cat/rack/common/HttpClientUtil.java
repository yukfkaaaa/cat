package com.cmb.o2o.cat.rack.common;

/**
 * Created by qiqing on 2019/7/14.
 */


import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * HttpClient工具类
 *
 * @return
 * @author zhaoshouyun
 * @create 2018年07月09日
 */
public class HttpClientUtil {

    public static final Logger logger= LoggerFactory.getLogger(HttpClientUtil.class);

    static final int conn_timeOut = 4 * 1000;

    static final int conn_request_timeOut = 2 * 1000;

    static final int read_timeOut = 3 * 1000;

    private static CloseableHttpClient httpClient = createHttpClient(800,10);


    private static void config(HttpRequestBase httpRequestBase) {

        httpRequestBase.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(conn_request_timeOut)
                .setConnectTimeout(conn_timeOut).setSocketTimeout(read_timeOut).build();
        httpRequestBase.setConfig(requestConfig);
    }


    /**
     * 创建HttpClient对象
     *
     * @return
     * @author zhaoshouyun
     * @create 2018年07月09日
     */
    private static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute) {

        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory
                .getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory> create().register("http", plainsf)
                .register("https", sslsf).build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(
                registry);
        // 将最大连接数增加
        cm.setMaxTotal(maxTotal);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(maxPerRoute);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception,
                                        int executionCount, HttpContext context) {
                if (executionCount >= 2) {// 如果已经重试了2次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext
                        .adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler).evictExpiredConnections().evictIdleConnections(60, TimeUnit.SECONDS).build();

        return httpClient;
    }

    private static void setPostParams(HttpPost httpost, Map<String, Object> params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * GET请求URL获取内容
     *
     * @param url
     * @return
     * @author zhaoshouyun
     * @throws Exception
     * @create 2018年07月09日
     */
    public static String post(String url, Map<String, Object> params) throws IOException {
        HttpPost httppost = new HttpPost(url);
        config(httppost);
        setPostParams(httppost, params);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httppost);

            logger.info("statusCode:{},url:{}",response.getStatusLine().toString(),url);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            return result;
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * GET请求URL获取内容
     *
     * @param url
     * @return
     * @author zhaoshouyun
     * @create 2018年07月09日
     */
    public static String get(String url) throws IOException,URISyntaxException {
        URL urlobj=new URL(url);
        URI uri = new URI(urlobj.getProtocol(), urlobj.getHost(), urlobj.getPath(), urlobj.getQuery(), null);
        HttpGet httpget = new HttpGet(uri);
        config(httpget);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            return result;
        }finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                //ignore
            }
        }
    }

    public static String getFile(String url,String savePath)throws IOException{
        HttpGet httpget = new HttpGet(url);
        config(httpget);

        FileOutputStream fileOutputStream=new FileOutputStream(savePath);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            if(response.getStatusLine().getStatusCode()!=200){

                EntityUtils.consume(response.getEntity());
                return null;
            }else {
                HttpEntity entity=response.getEntity();
                FileCopyUtils.copy(entity.getContent(),fileOutputStream);
                return savePath;
            }

        }finally {
            try {
                fileOutputStream.close();
                if (response != null)
                    response.close();
            } catch (IOException e) {
                //ignore
            }
        }


    }


}

