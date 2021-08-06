package com.demo.raft.communication.http;

import com.demo.raft.communication.http.model.RaftMessages;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author CoreyChen Zhang
 * @date 2021/8/5 20:15
 * @modified
 */
public class HttpRaftClient {

    private static final String APPLICATION_JSON = "application/json";

    public void SendRaftMessages(String[] ips) {
        RaftMessages raftMessages = new RaftMessages();
        raftMessages.setStatue("leader");
        for (String ip : ips) {
            doPost("http://" + ip + ":9110", JSON.toJSONString(raftMessages));
        }
    }

    public void doPost(String url, String bodyStr) {

        // Get http client
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // Creat post
        HttpPost httpPost = new HttpPost(url);

        StringEntity stringEntity = new StringEntity(bodyStr, Charset.forName("UTF-8"));

        httpPost.setEntity(stringEntity);
        httpPost.setHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();

            System.out.println("Response:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("Response length:" + responseEntity.getContentLength());
                System.out.println("Response content:" + EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                // close
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
