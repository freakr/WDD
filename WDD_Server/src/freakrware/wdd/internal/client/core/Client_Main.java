package freakrware.wdd.internal.client.core;

import freakrware.wdd.server.resources.Server_Setup;
import freakrware.wdd.server.resources.WDD_interface;

public class Client_Main implements WDD_interface{
	
	public static void main(String[] args) {
		Thread Online_Thread = new Thread (new Runnable() {
			public void run() {
				Thread t1 = Thread.currentThread();
				t1.setName("CONNECTION_KEEP Thread");
				Server_Setup setup = new Server_Setup();
				setup.set_Parameter(ONLINE_THREAD, ONLINE_THREAD_NOT_RUNNING);
				System.out.println(setup.get_Parameter(ONLINE_THREAD));
				while(true){
					System.out.println(setup.get_Parameter(ONLINE_THREAD));
					if(setup.get_Parameter(ONLINE_THREAD).equals(ONLINE_THREAD_NOT_RUNNING)){
						setup.set_Parameter(ONLINE_THREAD, ONLINE_THREAD_RUNNING);
						new Thread(new Client(setup,LOCALHOST,CONNECTION_KEEP, null)).start();
					}
					try {
						Thread.sleep(11000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		Online_Thread.start();
	}
}