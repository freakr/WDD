package freakrware.wdd.server.resources;

public interface DataBase_interface {

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
		final static String[] GETTER_USER = {DB_COL_USERNAME};
		final static String[] GETTER_MESSAGEBOARD = {DB_COL_ACTIONID,DB_COL_SENTFROMUSERID,DB_COL_SENTTOUSERID,DB_COL_SENTTRUE,DB_COL_MESSAGEID,DB_COL_INCOMINGDATE};
		final static String[] GETTER_LASTIP = {DB_COL_LASTUSERIP};
		final static String[] GETTER_USER_ONLINE = {DB_COL_USERNAME};
		final static String[] GETTER_USER_NAME = {DB_COL_USERNAME};
		final static String[] GETTER_MESSAGE_EXISTS = {DB_COL_MESSAGEID};
		final static String[] GETTER_MESSAGE = {DB_COL_MESSAGETEXT};
		final static String[] GETTER_TIME = {DB_COL_INCOMINGDATE};
		final static String[] GETTER_NEW_MESSAGE = {DB_COL_SENTFROMUSERID,DB_COL_MESSAGEID,DB_COL_INCOMINGDATE};
		final static String[] GETTER_NEW_MESSAGES_EXISTS = {DB_COL_ACTIONID};
		
	
}
