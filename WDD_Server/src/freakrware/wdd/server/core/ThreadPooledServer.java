package freakrware.wdd.server.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import freakrware.wdd.server.resources.DataBase;
import freakrware.wdd.server.resources.Server_Setup;
import freakrware.wdd.server.resources.WDD_interface;
import freakrware.wdd.server.ui.SysTray;

public class ThreadPooledServer implements Runnable,WDD_interface{

    protected int          serverPort;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    protected ExecutorService threadPool =
        Executors.newFixedThreadPool(THREAD_POOL_COUNT);
	public SysTray tray;
	public Server_Setup setup = new Server_Setup();
	private DataBase DB;
	

    public ThreadPooledServer(int port, DataBase DB){
        this.serverPort = port;
        this.DB = DB;
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                	this.threadPool.shutdown();
                    System.out.println("Server Stopped.") ;
                    tray.update(setup.get_Parameter(SERVERSTATUS));
                    return;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            tray.clientip = clientSocket.getLocalSocketAddress();
            tray.update("C");
            this.threadPool.execute(
                new WorkerRunnable(clientSocket,tray,setup,DB));
        }
        tray.update(setup.get_Parameter(SERVERSTATUS));
        this.threadPool.shutdownNow();
        System.out.println("Server Stopped.") ;
    }


    public synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
        	this.serverSocket.close();
            setup.set_Parameter(SERVERSTATUS, SERVERSTATUS_OFF);
            System.out.println(setup.get_Parameter(SERVERSTATUS));
            tray.update(setup.get_Parameter(SERVERSTATUS));
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            setup.set_Parameter(SERVERSTATUS, SERVERSTATUS_ON);
            System.out.println(setup.get_Parameter(SERVERSTATUS));
            tray.update(setup.get_Parameter(SERVERSTATUS));
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port "+PORT, e);
        }
    }
}
