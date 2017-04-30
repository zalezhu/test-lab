package com.zale.tools.clawer.web.cfg;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zale.tools.task.TaskExecutor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by Zale on 16/6/29.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {


    @Bean
    public HttpMessageConverters messageConverters(){
        FastJsonHttpMessageConverter fastjson = new FastJsonHttpMessageConverter();
        FormHttpMessageConverter httpMessageConverter = new FormHttpMessageConverter();
        return new HttpMessageConverters(fastjson,httpMessageConverter);
    }
    @Value("${clawer.nodeId}")
    private String nodeId;
    @Value("${clawer.weight}")
    private Integer weight;
    @Value("${server.port}")
    private String port;

    @Bean
    @Scope("singleton")
    public TaskExecutor taskExecutor() throws UnknownHostException, SocketException {
        TaskExecutor taskExecutor = new TaskExecutor();
        taskExecutor.setCrtTime(DateFormatUtils.format(new Date(),"yyyyMMdd"));
        taskExecutor.setName(nodeId);
        taskExecutor.setRunning(false);
        taskExecutor.setWeight(weight);
        taskExecutor.setCallbackAddr("http://"+getLocalRealIp()+":"+port+"/");
        return taskExecutor;
    }
    private  String getLocalRealIp() throws SocketException {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP

        Enumeration<NetworkInterface> netInterfaces;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

}
