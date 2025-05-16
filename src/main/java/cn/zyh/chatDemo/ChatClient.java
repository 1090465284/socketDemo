package cn.zyh.chatDemo;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "localhost";
	private static final int SERVER_PORT = 9090;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private Scanner scanner; // 声明Scanner为类的成员变量

	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in); // 用于读取用户名的临时Scanner
		System.out.print("请输入用户名: ");
		String username = inputScanner.nextLine();
		new ChatClient().start(username);
		inputScanner.close(); // 关闭临时Scanner
	}

	public void start(String username) {
		scanner = new Scanner(System.in); // 初始化成员变量Scanner
		try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(username); // 发送用户名到服务端

			// 启动消息接收线程
			new Thread(() -> {
				try {
					String serverMessage;
					while ((serverMessage = in.readLine()) != null) {
						System.out.println(serverMessage);
					}
				} catch (IOException e) {
					System.err.println("接收消息失败: " + e.getMessage());
				}
			}).start();

			// 发送消息循环
			String message;
			while (true) {
				message = scanner.nextLine(); // 使用成员变量scanner
				if ("exit".equalsIgnoreCase(message)) break;
				out.println(message);
			}
		} catch (IOException e) {
			System.err.println("无法连接服务端: " + e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			scanner.close(); // 关闭Scanner
		}
	}
}
