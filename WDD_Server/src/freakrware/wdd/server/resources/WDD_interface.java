package freakrware.wdd.server.resources;


//Interface to work together

public interface WDD_interface {
	// Socket Commandos
	public static final String CONNECTION_CLOSE = "Connection_Close";
	public static final String CONNECTION_REQUEST = "Connection_Request";
	public static final String CONNECTION_ACCEPTED = "Connection_Accepted";
	public static final String CONNECTION_KEEP = "Connection_Keep";
	public static final String DB_UPDATE_FULL = "DB_Full_Update";
	public static final String ACTION_COMPLETE = "Action_Complete";
	public static final String FILE_COMPLETE = "File_Complete";
	public static final String REQUEST_DB_FILES = "Request_DB_Files";
	public static final String REQUEST_URL = "Request_Url";
	public static final String OPEN_LINK = "Open_Link";
	// Socket Connection
	public static final String LOCALHOST = "localhost";
	public static final String HOST = "freakr.spdns.de";
	public static final int PORT = 15000;
	public static final int THREAD_POOL_COUNT = 20;
	// Datenbank 
	public static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
	public static final String DB_FOLDER = "WDDData/";
	public static final String DB_NAME = "DBWDD";
	// Sonstige
	public final static String SETUP = "Setup";
	// Client Parameter
	static final String ONLINE_THREAD = "Online_Thread";
	static final String ONLINE_THREAD_NOT_RUNNING = "not_running";
	static final String ONLINE_THREAD_RUNNING = "running";
	final static String ONLINESTATUS = "Onlinestatus";
	final static String ONLINESTATUS_ON = "On";
	final static String ONLINESTATUS_OFF = "Off";
	final static String SERVERSTATUS = "Serverstatus";
	final static String SERVERSTATUS_ON = "On";
	final static String SERVERSTATUS_OFF = "Off";
	
}
