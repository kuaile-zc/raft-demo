package com.demo.raft.communication.http;

import com.demo.raft.communication.RaftCommunicator;
import com.demo.raft.config.LoadConfig;
import com.demo.raft.constant.CommonConstant;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author CoreyChen Zhang
 * @date 2021/8/5 19:04
 * @modified
 */
public class HttpRaftCommunicator implements RaftCommunicator {

    HttpRaftService httpRaftService;

    HttpRaftClient httpRaftClient;

    public HttpRaftCommunicator() {
        init();
    }

    private void init(){
        httpRaftService = new HttpRaftService();
        httpRaftClient = new HttpRaftClient();
        LoadConfig config = new LoadConfig();
        config.getConfigFromResources();
    }

    @Override
    public void start() {
        Runnable s = () -> httpRaftService.await();
        Thread service = new Thread(s);
        service.start();

        String[] ips = System.getProperty(CommonConstant.IP_NODE_STR).split(CommonConstant.COMMA);
        httpRaftClient.SendRaftMessages(ips);

    }

    @Override
    public void stop() {

    }
}
