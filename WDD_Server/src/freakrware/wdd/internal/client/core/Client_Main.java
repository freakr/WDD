package freakrware.wdd.internal.client.core;

import freakrware.wdd.server.resources.SFCP_interface;
import freakrware.wdd.server.resources.Server_Setup;
import freakrware.wdd.server.resources.Standards_interface;
import freakrware.wdd.server.resources.WDD_interface;

public class Client_Main implements WDD_interface,SFCP_interface,Standards_interface{
	
	public static void main(String[] args) {
		Server_Setup setup = new Server_Setup();
		Standards st = new Standards();
		//connection_keep();
		new Thread(new Client(setup,LOCALHOST,REQUEST_ADD_MESSAGE, new String[] {"FreakR","FreakR1","TEST 9"})).start();
		st.wait(5000);
		//new Thread(new Client(setup,LOCALHOST,REQUEST_NEW_MESSAGES_FROM_BOARD, new String[] {"FreakR1"})).start();
		//new Thread(new Client(setup,LOCALHOST,ONLINESTATUS, new String[] {"FreakR1",ONLINESTATUS_ON,LOCALHOST})).start();
		st.wait(5000);
		//new Thread(new Client(setup,LOCALHOST,ONLINESTATUS, new String[] {"FreakR1",ONLINESTATUS_OFF,LOCALHOST})).start();
		//new Thread(new Client(setup,LOCALHOST,REQUEST_ADD_USER, new String[] {"FreakR1"})).start();
	}
	
}