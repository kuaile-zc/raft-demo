package com.demo.raft.communication.http.model;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @author CoreyChen Zhang
 * @date 2021/7/30 17:32
 * @modified
 */
public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;
    PrintWriter writer;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    /* This method is used to serve a static page */
    public void sendStaticResource() throws IOException {

        try {
            String responseMessage = JSON.toJSONString(new ResponseMessage("200", "ok"));
            int length = responseMessage.length();
            String errorMessage = "HTTP/1.1 200 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: "+length+"\r\n" +
                    "\r\n" +
                    responseMessage;
            output.write(errorMessage.getBytes());
        }
        catch (FileNotFoundException e) {
            String errorMessage = "HTTP/1.1 500 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 22\r\n" +
                    "\r\n" +
                    "<h1>Service error</h1>";
            output.write(errorMessage.getBytes());
        }
    }


    /** implementation of ServletResponse  */
    public void flushBuffer() throws IOException {
    }

    public int getBufferSize() {
        return 0;
    }

    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    public Locale getLocale() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        // autoflush is true, println() will flush,
        // but print() will not.
        writer = new PrintWriter(output, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {
    }

    public void resetBuffer() {
    }

    public void setBufferSize(int size) {
    }

    public void setContentLength(int length) {
    }

    @Override
    public void setContentLengthLong(long len) {

    }

    public void setContentType(String type) {
    }

    public void setLocale(Locale locale) {
    }
}
