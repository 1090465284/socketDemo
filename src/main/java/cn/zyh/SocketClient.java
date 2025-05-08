package cn.zyh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Description TODO
 * @Author ZhangYH
 * @Date 2025/5/8 22:37
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        // 创建socket 端口随机分配
        Socket socket = new Socket();
        // 使用这个socket进行连接
        socket.connect(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8888));

        //发收消息
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("hello server".getBytes());

        byte[] buffer = new byte[1024 * 1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1)
        {
            System.out.println(new String(buffer,0,len));
        }

//        outputStream.close();
//        socket.close();

    }
}
