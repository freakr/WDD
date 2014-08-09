package freakrware.wdd.server.core;

import freakrware.wdd.server.resources.WDD_interface;
import freakrware.wdd.server.ui.SysTray;

public class ServerMain implements WDD_interface{

	public static void main(String[] args) {
		ThreadPooledServer server = new ThreadPooledServer(PORT);
		SysTray st = new SysTray(server);
		server.tray = st;
		st.start();
		new Thread(server).start();
}

}
