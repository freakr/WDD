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
	private String[] arguments;
	private Socket server;
	private String command;

	public SFCP_Client(String line, BufferedReader input, PrintWriter output, Server_Setup setup, String[] arguments, Socket server, String command) throws IOException {
		this.line = line;
		this.input = input;
		this.output = output;
		this.setup = setup;
		this.arguments = arguments;
		this.server = server;
		this.command = command;
		work();
	}

	@SuppressWarnings("null")
	private String work() throws IOException {
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
			for(int x=0;x < arguments.length;x++){
				output.println(arguments[x]);
			}
    		break;
		case REQUEST_NEW_MESSAGES_FROM_BOARD:
			for(int x=0;x < arguments.length;x++){
				output.println(arguments[x]);
			}
			line = input.readLine();
			System.out.println(line);
			if(line.equals(NO_NEW_MESSAGES_FROM_BOARD)){
				output.println(CONNECTION_CLOSE);
				break;
			}
			else{
				String[][] messages = new String[Integer.parseInt(line)/3][3];
				for(int x=0;x< (Integer.parseInt(line)/3);x++){
					messages[x][0] = input.readLine();
					System.out.println(messages[x][0]);
					messages[x][1] = input.readLine();
					System.out.println(messages[x][1]);
					messages[x][2] = input.readLine();
					System.out.println(messages[x][2]);
				}
				output.println(ALL_MESSAGES_RECEIVED);
			}
			break;
		case REQUEST_ADD_MESSAGE:
			for(int x=0;x < arguments.length;x++){
				output.println(arguments[x]);
			}
    		break;
		case REQUEST_ADD_USER:
			for(int x=0;x < arguments.length;x++){
				output.println(arguments[x]);
			}
    		break;
		case REQUEST_REMOVE_USER:
			for(int x=0;x < arguments.length;x++){
				output.println(arguments[x]);
			}
    		break;
		case CONNECTION_ACCEPTED:
			output.println(command);
			break;
		case ACTION_COMPLETE:
			output.println(CONNECTION_CLOSE);
			break;
		default:
			output.println(CONNECTION_CLOSE);
			return line;
		}
		return line;
	}
}
