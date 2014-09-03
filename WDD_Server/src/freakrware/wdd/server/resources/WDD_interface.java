package freakrware.wdd.server.resources;


//Interface to work together

public interface WDD_interface {
	// Socket Commandos
	static final String CONNECTION_CLOSE = "Connection_Close";
	static final String CONNECTION_REQUEST = "Connection_Request";
	static final String CONNECTION_ACCEPTED = "Connection_Accepted";
	static final String CONNECTION_KEEP = "Connection_Keep";
	static final String DB_UPDATE_FULL = "DB_Full_Update";
	static final String ACTION_COMPLETE = "Action_Complete";
	static final String FILE_COMPLETE = "File_Complete";
	static final String REQUEST_DB_FILES = "Request_DB_Files";
	static final String REQUEST_URL = "Request_Url";
	static final String REQUEST_ADD_USER = "Request_add_User";
	static final String REQUEST_REMOVE_USER = "Request_remove_User";
	static final String REQUEST_ADD_MESSAGE = "Request_add_Message";
	static final String MESSAGE_ADDED = "Message_added";
	static final String MESSAGE_NOT_ADDED = "Message_not_added";
	static final String OPEN_LINK = "Open_Link";
	static final String SENDER_UNKNOWN = "Sender_Unknown";
	static final String RECEIVER_UNKNOWN = "Receiver_Unknown";
	static final String USER_EXISTS = "User_exists";
	static final String USER_NOT_EXISTS = "User_not_exists";
	static final String USER_ADDED = "User_added";
	static final String USER_NOT_ADDED = "User_not_added";
	static final String USER_REMOVED = "User_removed";
	static final String USER_NOT_REMOVED = "User_not_removed";
	// Socket Connection
	static final String LOCALHOST = "localhost";
	static final String HOST = "freakr.spdns.de";
	static final int PORT = 15000;
	static final int THREAD_POOL_COUNT = 20;
	// DataBase Basic Parameter 
	static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
	static final String DB_FOLDER = "WDDData/";
	static final String DB_NAME = "DBWDD";
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
	// DataBase Getter
	final static String[] GETTER_USER_EXISTS = {DB_COL_USERID};
	final static String[] GETTER_MESSAGE_EXISTS = {DB_COL_MESSAGEID};
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
