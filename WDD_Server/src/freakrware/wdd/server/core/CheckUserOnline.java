package freakrware.wdd.server.core;

import freakrware.wdd.internal.client.core.Client;
import freakrware.wdd.server.resources.DataBase;
import freakrware.wdd.server.resources.SFCP_interface;
import freakrware.wdd.server.resources.Server_Setup;
import freakrware.wdd.server.resources.WDD_interface;

public class CheckUserOnline implements Runnable,WDD_interface,SFCP_interface{
	
	private DataBase DB = new DataBase();
	ThreadPooledServer server;
	private Server_Setup setup = new Server_Setup();

	
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		t.setName("CheckUserOnline" + " - Thread");
		String[] result;
		while(!server.isStopped){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			result = DB.checkuseronline();
			for(int x = 0; x < result.length;x++){
				new Thread(new Client(setup,DB.lastip(result[x]),REQUEST_NEW_MESSAGES_FROM_BOARD, new String[] {result[x]})).start();
			}
		}
		
	}

}
