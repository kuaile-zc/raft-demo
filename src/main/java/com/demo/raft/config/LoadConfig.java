package com.demo.raft.config;

import com.demo.raft.constant.CommonConstant;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is for load application.yml.
 * @author CoreyChen Zhang
 * @date 2021/8/4 17:56
 * @modified
 */
public class LoadConfig {

    final private static String USER_DIR = "user.dir";
    final private static String APPLICATION_YML_PATH ="src" + File.separator + "main" + File.separator +  "resources" + File.separator + "application.yml";

    final private static String APPLICATION_CONFIG = System.getProperty(USER_DIR) + File.separator + APPLICATION_YML_PATH;

    final private static String RAFT_NODE_STR = "raft.node=";


    public void getConfigFromResources() {
        File configFile = new File(APPLICATION_CONFIG);
        if (!configFile.exists()) {
            System.out.println("The config file read fail!");
        }
        String configStr = readConfigFile(configFile);
        System.setProperty(CommonConstant.IP_NODE_STR, parseIp(configStr));
    }

    private String readConfigFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            char[] chars = new char[1024];
            int len;
            while ((len = fileReader.read(chars) )!= -1){
                String str = new String(chars, 0, len);
                stringBuilder.append(str);
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private String parseIp(String str) {
        if (str == null){
            return "";
        }
        int beginIndex = str.indexOf(RAFT_NODE_STR) + RAFT_NODE_STR.length();
        int endIndex = str.indexOf(CommonConstant.SEMICOLON);
        return str.substring(beginIndex, endIndex);
    }

}
