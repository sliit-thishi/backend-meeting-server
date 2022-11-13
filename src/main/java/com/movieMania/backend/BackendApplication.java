package com.movieMania.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Stack;

@SpringBootApplication
public class BackendApplication {

	private static Stack<String> a_server = new Stack<>();
	private static Stack<String> b_server = new Stack<>();


	private static boolean typeLogic = false;

	private static String type = "";

	public String setVal(String val) {
		a_server.push(val);
		return "added";
	}


	public static String getType(){
		return type;
	}
	public String setType(){
		typeLogic = true;
		return "set";
	}

	public static String typeSet(){
		typeLogic = true;
		return "set";
	}

	public String stop(){
		typeLogic=false;
		return "stopped";
	}

	public static String getVal() {
		while (!a_server.empty()){
			b_server.push(a_server.pop());
		}
		if (b_server.empty()){
			return "empty";
		}
		return b_server.pop();
	}


	private static void serverMethod() throws IOException {
		ServerSocket ss = new ServerSocket(4999);
		System.out.println("socket started");
		Socket s = ss.accept();
		System.out.println("client1 connected");
		for (;;){



			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);

//			String str= bf.readLine();
//			System.out.println("Client says that "+str);

			PrintWriter pr = new PrintWriter(s.getOutputStream());
			String word = getVal();
			if (!word.equalsIgnoreCase("empty")){
				pr.println(word);
				pr.flush();
			}
			if (!typeLogic){
				System.out.println("stopped");
				pr.println("over");
				pr.flush();
				String res = typeSet();
				System.out.println(res);

			}

		}
	}
	public static void main(String[] args) throws IOException {
		SpringApplication.run(BackendApplication.class, args);

		for (;;){
			while (!typeLogic){
				System.out.println(typeLogic);
			}
			serverMethod();
		}

	}

}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enable", matchIfMissing = true)
class SchedulingConfiguration{

}
