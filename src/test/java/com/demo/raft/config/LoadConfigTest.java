package com.demo.raft.config;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author CoreyChen Zhang
 * @date 2021/8/4 18:56
 * @modified
 */
public class LoadConfigTest {

    private LoadConfig loadConfig = new LoadConfig();

    @Test
    public void getConfig() {
        String configFromResources = loadConfig.getConfigFromResources();
        Assert.assertNotNull(configFromResources);
    }
}
