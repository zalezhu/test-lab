package com.zale.tools.task.ping;

import com.zale.tools.task.utils.HttpClientProvider;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Zale on 2017/5/2.
 */
public class HttpPing implements Ping{
    @Override
    public boolean ping(String addr) {
        HttpGet get =HttpClientProvider.getInstance().getHttpGet(addr+"/ping",new HashMap());
        try {
            CloseableHttpResponse response = HttpClientProvider.getInstance().getHttpClient().execute(get);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean resolve(String addr) {
        if(addr.startsWith("http")){
            return true;
        }
        return false;
    }
}
