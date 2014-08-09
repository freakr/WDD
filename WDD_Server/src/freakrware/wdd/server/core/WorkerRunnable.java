package freakrware.wdd.server.core;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

import freakrware.wdd.server.resources.DataBase;
import freakrware.wdd.server.resources.Server_Setup;
import freakrware.wdd.server.resources.WDD_interface;
import freakrware.wdd.server.ui.SysTray;

public class WorkerRunnable implements Runnable,WDD_interface{

    protected Socket clientSocket = null;
    private SysTray tray;
	private Server_Setup setup;
	private DataBase DB;
    

    public WorkerRunnable(Socket clientSocket, SysTray tray, Server_Setup setup, DataBase DB) {
        this.clientSocket = clientSocket;
        this.tray   = tray;
        this.setup = setup;
        this.tray.clientip = clientSocket.getLocalSocketAddress();
        this.DB = DB;
      
    }

    public void run() {
        try {
        	BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        	PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
        	String line; 
        	
            while( (line = input.readLine())!=null){
            	System.out.println(line);
            	String serverstatus = setup.get_Parameter(SERVERSTATUS);
            	if (serverstatus.equals(SERVERSTATUS_OFF)){
            		line = CONNECTION_CLOSE;
            	}
            	new SFCP_Server(line,input,output,DB);
            	
            }  
        } catch (IOException e) {
        	tray.update("A");
        	System.out.println(e.getMessage());
        	//e.printStackTrace();
        }
    }
}