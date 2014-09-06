package freakrware.wdd.server.core;

import freakrware.wdd.server.resources.DataBase;
import freakrware.wdd.server.resources.WDD_interface;
import freakrware.wdd.server.ui.SysTray;

public class ServerMain implements WDD_interface{

	public static void main(String[] args) {
		DataBase DB = new DataBase();
		ThreadPooledServer TPserver = new ThreadPooledServer(PORT,DB);
		CheckUserOnline CUO = new CheckUserOnline();
		SysTray st = new SysTray(TPserver,DB);
		TPserver.tray = st;
		CUO.server = TPserver;
		st.start();
		new Thread(TPserver).start();
		new Thread(CUO).start();
	}

}
