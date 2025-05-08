package cn.zyh.httpdemo;


import java.util.HashMap;
import java.util.Map;

/**
 * 将接收的请求报文转化为请求对象
 */
public class Request {

    private String protocol;
    // 请求方式
    private String type;
    // uri
    private String uri;
    // 请求头
    private Map<String,String> header = new HashMap<>();
    // 请求体
    private String body;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String> getHeaders() {
        return header;
    }

    public void setHeaders(Map<String, String> header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeader(String key){
        return header.get(key);
    }

    public void addHeader(String key,String value){
        header.put(key,value);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return "Request{" +
                "protocol='" + protocol + '\'' +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                ", header=" + header +
                ", body='" + body + '\'' +
                '}';
    }
}
