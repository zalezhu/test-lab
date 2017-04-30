package com.zale.tools.task.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import java.util.Map;

/**
 * Created by zhaoyang on 04/19/2017.
 */

public class HttpClientProvider {

    private static final Logger logger = Logger.getLogger(HttpClientProvider.class);

    private CloseableHttpClient httpClient;
    private PoolingHttpClientConnectionManager connManager;
    private volatile static HttpClientProvider singleton;
    public static HttpClientProvider getInstance() {
        if (singleton == null) {
            synchronized (HttpClientProvider.class) {
                if (singleton == null) {
                    singleton = new HttpClientProvider();
                }
            }
        }
        return singleton;
    }
    private HttpClientProvider() {
        try {
            this.connManager = new PoolingHttpClientConnectionManager();
            this.connManager.setMaxTotal(500);
            this.connManager.setDefaultMaxPerRoute(500);
            this.httpClient = HttpClients.custom()
                    .setConnectionManager(this.connManager).build();
            new IdleConnectionCleanThread(this.connManager);
        } catch (Throwable th) {
            logger.error("<<<<<< Init Http Executor On Error", th);
        }
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public HttpGet getHttpGet(String url, Map<String, Object> params) {
        String queryStr = UrlParamUtil.createLinkString(params);
        if (!StringUtils.isEmpty(queryStr)) {
            queryStr = "?" + queryStr;
        }
        HttpGet httpGet = new HttpGet(url + queryStr);
        httpGet.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(15000)
                .setSocketTimeout(45000).build()
        );
        return httpGet;
    }

    public HttpPost getHttpPost(String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(15000)
                .setSocketTimeout(45000).build()
        );
        return httpPost;
    }
}
