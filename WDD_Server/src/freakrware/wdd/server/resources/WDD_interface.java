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
	// DataBase Basic Parameter 
	public static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
	public static final String DB_FOLDER = "WDDData/";
	public static final String DB_NAME = "DBWDD";
	// DataBase Getter
	final static String[] GETTER_USER_EXISTS = {"UserID"};
	// DataBase Table-Names
	final static String DB_TABLE_USERNAME = "Username";
	final static String DB_TABLE_USERDATA = "Userdata";
	final static String DB_TABLE_MESSAGES = "Messages";
	final static String DB_TABLE_MESSAGEBOARD = "Messageboard";
	// DataBase Columns
	final static String DB_COL_USERNAME = "Username";
	final static String DB_COL_USERID = "UserID";
	final static String DB_COL_USERIP = "UserIP";
	final static String DB_COL_MESSAGEID = "MessageID";
	final static String DB_COL_MESSAGETEXT = "Messagetext";
	final static String DB_COL_ACTIONID = "ActionID";
	final static String DB_COL_SENTFROMUSERID = "SentFromUserID";
	final static String DB_COL_SENTTOUSERID = "SentToUserID";
	final static String DB_COL_SENTTRUE = "SentTrue";
	final static String DB_COL_INCOMINGDATE = "IncomingDate";
	// Others
	public final static String SETUP = "Setup";
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
