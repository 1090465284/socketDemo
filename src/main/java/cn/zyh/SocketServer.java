package cn.zyh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description TODO
 * @Author ZhangYH
 * @Date 2025/5/8 22:32
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket();
        // 绑定ip和端口
        serverSocket.bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8888));
        // 监听在这个端口上 阻塞
        Socket socket = serverSocket.accept();


        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        byte[] buffer = new byte[1024 * 1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1){
            System.out.println(new String(buffer,0,len));
            outputStream.write("I reviced your message".getBytes());
        }
//        inputStream.close();
//        socket.close();
    }

// 多客户端伪代码
//    while(true)
//    {
//        Socket socket = serverSocket.accept();
//        new ServerThread(socket).start();
//    }
}
