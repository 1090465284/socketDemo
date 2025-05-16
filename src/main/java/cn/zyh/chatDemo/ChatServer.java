package cn.zyh.chatDemo;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	private static final int PORT = 9090;
	private static Set<ClientHandler> clients = new HashSet<>();

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("服务端启动，监听端口: " + PORT);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("新客户端连接: " + clientSocket);
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				clients.add(clientHandler);
				new Thread(clientHandler).start();
			}
		} catch (IOException e) {
			System.err.println("服务端异常: " + e.getMessage());
		}
	}

	// 广播消息给所有客户端
	public static void broadcast(String message, ClientHandler excludeClient) {
		for (ClientHandler client : clients) {
			if (client != excludeClient) {
				client.sendMessage(message);
			}
		}
	}

	// 移除断开连接的客户端
	public static void removeClient(ClientHandler client) {
		clients.remove(client);
		System.out.println("客户端断开连接: " + client.getClientSocket());
	}
}
