package cn.zyh.chatDemo;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private String username;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			username = in.readLine(); // 客户端连接时发送用户名
			ChatServer.broadcast(username + " 加入了聊天室", null);
		} catch (IOException e) {
			System.err.println("初始化客户端失败: " + e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			String clientMessage;
			while ((clientMessage = in.readLine()) != null) {
				System.out.println(username + ": " + clientMessage);
				ChatServer.broadcast(username + ": " + clientMessage, this);
			}
		} catch (IOException e) {
			System.err.println("客户端连接异常: " + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ChatServer.removeClient(this);
			ChatServer.broadcast(username + " 离开了聊天室", null);
		}
	}

	public void sendMessage(String message) {
		out.println(message);
	}

	public Socket getClientSocket() {
		return clientSocket;
	}
}