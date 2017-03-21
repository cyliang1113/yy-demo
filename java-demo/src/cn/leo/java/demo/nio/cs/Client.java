package cn.leo.java.demo.nio.cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {


	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket client = new Socket("127.0.0.1", 9999);
		PrintWriter pw = new PrintWriter(client.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pw.write(br.readLine());
		pw.close();
		br.close();
	}
}
