package freakrware.wdd.server.core;

import freakrware.wdd.server.resources.DataBase;
import freakrware.wdd.server.resources.WDD_interface;
import freakrware.wdd.server.ui.SysTray;
import freakrware.wdd.server.ui.UI;

public class ServerMain implements WDD_interface{

	public static void main(String[] args) {
		DataBase db = new DataBase();
		ThreadPooledServer tpserver = new ThreadPooledServer(PORT,db);
		UI ui = new UI();
		CheckUserOnline cuo = new CheckUserOnline();
		SysTray st = new SysTray(tpserver,db,ui);
		tpserver.tray = st;
		cuo.server = tpserver;
		ui.server = tpserver;
		st.start();
		new Thread(tpserver).start();
		new Thread(cuo).start();
		ui.setVisible(true);
		}

}
