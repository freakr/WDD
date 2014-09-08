package freakrware.wdd.server.core;

import freakrware.wdd.server.resources.Interfaces;

public class ServerMain implements Interfaces{

	public static void main(String[] args) {
		//DataBase db = new DataBase();
		//final ThreadPooledServer tpserver = new ThreadPooledServer(PORT,db);
		//final UI ui = new UI();
		//final CheckUserOnline cuo = new CheckUserOnline();
		//final SysTray st = new SysTray(tpserver,db,ui);
		tpserver.tray = st;
		cuo.server = tpserver;
		ui.server = tpserver;
		st.start();
		new Thread(tpserver).start();
		new Thread(cuo).start();
		ui.setVisible(true);
		new Thread(new Runnable() { public void run() { 
			Thread t = Thread.currentThread();
			t.setName("DataRefresh" + " - Thread");
			  while(true){
				  ui.refresh_ui();
				  standard.wait(5000);
			  }
			}}).start();;
		}
}