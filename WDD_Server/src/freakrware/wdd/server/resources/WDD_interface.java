package freakrware.wdd.server.resources;


//Interface to work together

public interface WDD_interface {
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
	final static String DB_TABLE_USER = "User";
	final static String DB_TABLE_MESSAGES = "Messages";
	final static String DB_TABLE_MESSAGEBOARD = "Messageboard";
	// DataBase Columns
	final static String DB_COL_USERNAME = "UserName";
	final static String DB_COL_USERID = "UserID";
	final static String DB_COL_USERONLINESTATUS = "UserOnlineStatus";
	final static String DB_COL_LASTUSERIP = "LastUserIP";
	final static String DB_COL_MESSAGEID = "MessageID";
	final static String DB_COL_MESSAGETEXT = "Messagetext";
	final static String DB_COL_ACTIONID = "ActionID";
	final static String DB_COL_SENTFROMUSERID = "SentFromUserID";
	final static String DB_COL_SENTTOUSERID = "SentToUserID";
	final static String DB_COL_SENTTRUE = "SentTrue";
	final static String DB_COL_INCOMINGDATE = "IncomingDate";
	// DataBase Getter
	final static String[] GETTER_USER_EXISTS = {DB_COL_USERID};
	final static String[] GETTER_LASTIP = {DB_COL_LASTUSERIP};
	final static String[] GETTER_USER_ONLINE = {DB_COL_USERNAME};
	final static String[] GETTER_USER_NAME = {DB_COL_USERNAME};
	final static String[] GETTER_MESSAGE_EXISTS = {DB_COL_MESSAGEID};
	final static String[] GETTER_MESSAGE = {DB_COL_MESSAGETEXT};
	final static String[] GETTER_TIME = {DB_COL_INCOMINGDATE};
	final static String[] GETTER_NEW_MESSAGE = {DB_COL_SENTFROMUSERID,DB_COL_MESSAGEID,DB_COL_INCOMINGDATE};
	final static String[] GETTER_NEW_MESSAGES_EXISTS = {DB_COL_ACTIONID};
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
