package freakrware.wdd.server.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SFCP_Client implements WDD_interface {

	private String line;
	private BufferedReader input;
	private PrintWriter output;
	private Server_Setup setup;
	private String value;
	private Socket server;
	private String command;

	public SFCP_Client(String line, BufferedReader input, PrintWriter output, Server_Setup setup, String value, Socket server, String command) throws IOException {
		this.line = line;
		this.input = input;
		this.output = output;
		this.setup = setup;
		this.value = value;
		this.server = server;
		this.command = command;
		work();
	}

	private void work() throws IOException {
		switch (line) {
		case CONNECTION_KEEP:
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setup.set_Parameter(ONLINESTATUS, ONLINESTATUS_ON);
			System.out.println(setup.get_Parameter(ONLINESTATUS));
			output.println(CONNECTION_KEEP);
			break;
		case CONNECTION_CLOSE:
			output.println(line);
			server.shutdownInput();
			server.shutdownOutput();
			server.close();
			if (command.equals(CONNECTION_KEEP)) {
				setup.set_Parameter(ONLINE_THREAD,ONLINE_THREAD_NOT_RUNNING);
				System.out.println(setup.get_Parameter(ONLINE_THREAD));
				setup.set_Parameter(ONLINESTATUS,ONLINESTATUS_OFF);
				System.out.println(setup.get_Parameter(ONLINESTATUS));
			}
			break;
		case REQUEST_URL:
			output.println(value);
			break;
		case CONNECTION_ACCEPTED:
			output.println(command);
			break;
		case ACTION_COMPLETE:
			output.println(CONNECTION_CLOSE);
			break;
		default:
			output.println(CONNECTION_CLOSE);
			break;
		}
	}
}
