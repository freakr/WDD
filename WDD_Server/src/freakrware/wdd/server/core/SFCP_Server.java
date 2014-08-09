package freakrware.wdd.server.core;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

import freakrware.wdd.server.resources.WDD_interface;

/**
 * String formatted Connection Protokoll
 */

public class SFCP_Server implements WDD_interface{

	private String line;
	private BufferedReader input;
	private PrintWriter output;

	public SFCP_Server(String line, BufferedReader input, PrintWriter output) {
		this.line = line;
		this.input = input;
		this.output = output;
		work();
	}

	private void work() {
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
    		try {
				input.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
    		break;
    	case CONNECTION_REQUEST:
    		output.println(CONNECTION_ACCEPTED);
    		break;
    	case OPEN_LINK:
    		output.println(REQUEST_URL);
    		try {
				line = input.readLine();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
    		URI uri = null;
			try {
				uri = new URI(line);
				if (Desktop.isDesktopSupported()) {
        		      try {
        		        Desktop.getDesktop().browse(uri);
        		      } catch (IOException e) { 
        		    	  System.out.println(e.getMessage());
        		    	  }
        		    } else { 
        		    	System.out.println("Kein Desktop gefunden !");
        		    	}
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			output.println(ACTION_COMPLETE);
    		break;
    	default :
    		output.close();
    		try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		output.println(CONNECTION_CLOSE);
        	break;
    	}
		
	}

}
