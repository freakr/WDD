package freakrware.wdd.server.resources;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * String formatted Connection Protokoll
 */

public class SFCP_Server implements WDD_interface{

	private String line;
	private BufferedReader input;
	private PrintWriter output;
	private DataBase DB;

	public SFCP_Server(String line, BufferedReader input, PrintWriter output, DataBase DB) throws IOException {
		this.line = line;
		this.input = input;
		this.output = output;
		this.DB = DB;
		work();
	}

	private void work() throws IOException {
		switch(line){
    	case CONNECTION_KEEP:
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
    		output.println(CONNECTION_KEEP);
    		break;
    	case CONNECTION_CLOSE:
    		output.println(CONNECTION_CLOSE);
    		output.close();
    		input.close();
			
    		break;
    	case CONNECTION_REQUEST:
    		output.println(CONNECTION_ACCEPTED);
    		break;
    	case REQUEST_ADD_MESSAGE:
    		output.println(REQUEST_ADD_MESSAGE);
    		String stringsentfrom = input.readLine();
    		String stringsentto = input.readLine();
    		String message = input.readLine();
    		int sentfrom;
    		int sentto;
    		if(DB.user_exists(stringsentfrom)== 0){
    			output.println(SENDER_UNKNOWN);
    			break;
    		}else
    		{
    			sentfrom = DB.user_exists(stringsentfrom);
    		}
    		if(DB.user_exists(stringsentto)== 0){
    			output.println(RECEIVER_UNKNOWN);
    			break;
    		}else
    		{
    			sentto = DB.user_exists(stringsentto);
    		}
    		if(DB.message_exists(message) == 0){
    			DB.message_add(message);
    			DB.messageboard_add(sentfrom,sentto,DB.message_exists(message));
    		}else
    		{
    			DB.messageboard_add(sentfrom,sentto,DB.message_exists(message));
    		}
    		
    		System.out.println(MESSAGE_ADDED);
			break;
    	case REQUEST_ADD_USER:
    		output.println(REQUEST_ADD_USER);
    		line = input.readLine();
			if(DB.user_exists(line) != 0){
				System.out.println(USER_EXISTS);
				output.println(USER_EXISTS);
			}
			else{
				if(DB.user_add(line)){
					System.out.println(line + " added");
					output.println(USER_ADDED);
				}else
				{
					System.out.println(line + " not added");
					output.println(USER_NOT_ADDED);
				}
				
			}
			break;
    	case REQUEST_REMOVE_USER:
    		output.println(REQUEST_REMOVE_USER);
    		line = input.readLine();
			if(DB.user_exists(line) != 0){
				System.out.println(USER_EXISTS);
				if(DB.user_remove(line)){
					System.out.println(line + " removed");
					output.println(USER_REMOVED);
				}else
				{
					System.out.println(line + " not removed");
					output.println(USER_NOT_REMOVED);
				}
			}
			else{
				System.out.println(USER_NOT_EXISTS);
				output.println(USER_NOT_EXISTS);
			}
			break;	
    	case OPEN_LINK:
    		output.println(REQUEST_URL);
    		line = input.readLine();
			URI uri = null;
			try {
				uri = new URI(line);
				if (Desktop.isDesktopSupported()) {
        		     Desktop.getDesktop().browse(uri);
				}
        		else { 
        		    System.out.println("Kein Desktop gefunden !");
        		}
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			output.println(ACTION_COMPLETE);
    		break;
    	default :
    		output.println(CONNECTION_CLOSE);
    		output.close();
    		input.close();
			break;
    	}
		
	}

}
