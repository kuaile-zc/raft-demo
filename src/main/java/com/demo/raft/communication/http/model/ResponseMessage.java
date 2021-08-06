package com.demo.raft.communication.http.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author CoreyChen Zhang
 * @date 2021/8/6 20:15
 * @modified
 */
@Data
@AllArgsConstructor
public class ResponseMessage {

    private String code;

    private String message;
}
