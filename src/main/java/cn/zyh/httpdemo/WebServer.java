package cn.zyh.httpdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description TODO
 * @Author ZhangYH
 * @Date 2025/5/8 23:26
 */
public class WebServer {
    public static void main(String[] args) throws IOException {
//        demo1();
        demo2();
    }

    public static void demo1() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;

        while((len = inputStream.read(bytes)) != -1)
        {
            System.out.println(new String(bytes, 0, len));
        }
        inputStream.close();
        socket.close();
    }

    public static void demo2() throws IOException {
        // 创建一个服务器监听在8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket server = serverSocket.accept();
        OutputStream outputStream = server.getOutputStream();
        // 按照http协议的格式封装一个报文
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Length: 39\r\n" +
                "Content-Type: text/html;charset=UTF-8\r\n\r\n" +
                "<h1 style=\"color:red\">hello server!<h1>";
        // 将报文写出给浏览器
        outputStream.write(response.getBytes());
        outputStream.flush();
        // 这个输出流不要着急关，因为突然的关闭会导致浏览器和服务器的连接断开
    }

}
