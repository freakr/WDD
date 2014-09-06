package freakrware.wdd.server.resources;


//Interface to work together

public interface WDD_interface {
	// Socket Connection
	static final String LOCALHOST = "localhost";
	static final String HOST = "freakr.spdns.de";
	static final int PORT = 15000;
	static final int THREAD_POOL_COUNT = 20;
	// Others
	final static String SETUP = "Setup";
	// Client Parameter
	static final String ONLINE_THREAD = "Online_Thread";
	static final String ONLINE_THREAD_NOT_RUNNING = "not_running";
	static final String ONLINE_THREAD_RUNNING = "running";
	final static String ONLINESTATUS = "Onlinestatus";
	final static String ONLINESTATUS_ON = "On";
	final static String ONLINESTATUS_OFF = "Off";
	final static String SERVERSTATUS = "Serverstatus";
	final static String SERVERSTATUS_ON = "A";
	final static String SERVERSTATUS_OFF = "I";
	
	
}
