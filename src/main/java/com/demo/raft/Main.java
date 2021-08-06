package com.demo.raft;

import com.demo.raft.communication.RaftCommunicator;
import com.demo.raft.communication.http.HttpRaftCommunicator;
import com.demo.raft.config.LoadConfig;

import java.util.List;

/**
 * @author CoreyChen Zhang
 * @date 2021/8/4 16:08
 * @modified
 */
public class Main {


    public static void main(String[] args) {
        //TODO User a mode for the thread to run.

        RaftCommunicator raftCommunicator = new HttpRaftCommunicator();
        raftCommunicator.start();
    }
}
