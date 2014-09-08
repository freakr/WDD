package freakrware.wdd.internal.client.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import freakrware.wdd.server.resources.Interfaces;
import freakrware.wdd.server.resources.SFCP;
import freakrware.wdd.server.resources.Server_Setup;

public class Client implements Runnable,Interfaces {
	public String command;
	private String host;
	private int port;
	private String[] arguments;
	private Server_Setup setup;

	public Client(Server_Setup setup, String host, String command,String[] arguments) {
		this.setup = setup;
		this.host = host; // "192.168.42.174";
		this.port = PORT;
		this.command = command;
		this.arguments = arguments;
	}

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		t.setName(command + " - Thread");
		Socket server = new Socket();
		InetAddress ihost = null;
		try {
			ihost = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
		try {
			server.connect(new InetSocketAddress(ihost, port), 10000);
			BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));
			PrintWriter output = new PrintWriter(server.getOutputStream(), true);
			output.println(CONNECTION_REQUEST);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
				new SFCP(line,input,output,arguments,server,command);
				}
		} catch (IOException e) {
			if (command.equals(CONNECTION_KEEP)) {
				setup.set_Parameter(ONLINE_THREAD,ONLINE_THREAD_NOT_RUNNING);
				System.out.println(setup.get_Parameter(ONLINE_THREAD));
				setup.set_Parameter(ONLINESTATUS, ONLINESTATUS_OFF);
				System.out.println(setup.get_Parameter(ONLINESTATUS));
			}
			System.out.println(e.getMessage());
		}
	}
}