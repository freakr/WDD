package freakrware.wdd.server.core;

import freakrware.wdd.server.resources.DataBase;
import freakrware.wdd.server.resources.WDD_interface;
import freakrware.wdd.server.ui.SysTray;

public class ServerMain implements WDD_interface{

	public static void main(String[] args) {
		DataBase DB = new DataBase();
		ThreadPooledServer server = new ThreadPooledServer(PORT,DB);
		SysTray st = new SysTray(server,DB);
		server.tray = st;
		st.start();
		new Thread(server).start();
	}

}
