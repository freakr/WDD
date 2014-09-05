
package freakrware.wdd.server.resources;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import android.os.Environment;


public class DataBase implements WDD_interface{

	private static Connection dbVerbindung = null;
	private static Statement stmt = null;
	public ResultSet rs = null;
	public String strsql = null;
	
	{
	    try {
	        Class.forName(DB_DRIVER);
	    	
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	public DataBase(){
		
		initialise_database();
		
	}
	public int user_exists(String name){
		set_strsql("SELECT "+DB_COL_USERID+" FROM "+DB_TABLE_USERNAME+" WHERE "+DB_COL_USERNAME+" = '"+ name +"'");
		
		String[] result = get_data(GETTER_USER_EXISTS);
		for(int i=0; i< result.length;i++){
			 if(result[i] != null){
			 return Integer.parseInt(result[i]);
			 	}
			 }
		return 0;
		
	}
	@SuppressWarnings("null")
	public String[][] get_new_messages(int user) {
		set_strsql("SELECT "+DB_COL_ACTIONID+" FROM "+DB_TABLE_MESSAGEBOARD+" WHERE "+DB_COL_SENTTOUSERID+" = '"+ user +"' AND "+DB_COL_SENTTRUE+" = FALSE");
		String[] resultids = get_data(GETTER_NEW_MESSAGES_EXISTS);
		String[][] result = new String[resultids.length][GETTER_NEW_MESSAGE.length+1];
		for(int x=0;x<resultids.length;x++){
			result[x][3] = resultids[x];
			set_strsql("SELECT DISTINCT "+DB_COL_USERNAME+" FROM "+DB_TABLE_USERNAME+","+DB_TABLE_MESSAGEBOARD+" WHERE "+DB_COL_SENTFROMUSERID+" = "+DB_COL_USERID+" AND "+DB_COL_ACTIONID+" = '"+resultids[x]+"'");
			String [] resultone = get_data(GETTER_USER_NAME);
			result[x][0] = resultone[0];
			set_strsql("SELECT DISTINCT "+DB_COL_MESSAGETEXT+" FROM "+DB_TABLE_MESSAGES+","+DB_TABLE_MESSAGEBOARD+" WHERE "+DB_COL_ACTIONID+" = '"+ resultids[x] +"' AND "+DB_TABLE_MESSAGEBOARD+"."+DB_COL_MESSAGEID+" = "+DB_TABLE_MESSAGES+"."+DB_COL_MESSAGEID);
			String [] resulttwo = get_data(GETTER_MESSAGE);
			result[x][1] = resulttwo[0];
			set_strsql("SELECT DISTINCT "+DB_COL_INCOMINGDATE+" FROM "+DB_TABLE_MESSAGES+","+DB_TABLE_MESSAGEBOARD+" WHERE "+DB_COL_ACTIONID+" = '"+ resultids[x] +"'");
			String [] resultthree = get_data(GETTER_TIME);
			result[x][2] = resultthree[0];
		}
		
		return result;
	}
	public boolean messageboard_add(int sentfrom, int sentto,int message) {
		Timestamp time = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		set_strsql("INSERT INTO "+DB_TABLE_MESSAGEBOARD+" ("+DB_COL_SENTFROMUSERID+","+DB_COL_SENTTOUSERID+","+DB_COL_MESSAGEID+","+DB_COL_INCOMINGDATE+") VALUES ('"+ sentfrom +"','"+ sentto +"','"+ message +"','"+ time +"')"); 
		
		return set_data();
	}
	public boolean messages_senttrue(String actionid) {
		strsql = "UPDATE "+DB_TABLE_MESSAGEBOARD+" SET "+DB_COL_SENTTRUE+" = TRUE WHERE "+DB_COL_ACTIONID+" = " + actionid; 
		
		return set_data();
	}
	
	public int message_exists(String message){
		set_strsql("SELECT "+DB_COL_MESSAGEID+" FROM "+DB_TABLE_MESSAGES+" WHERE "+DB_COL_MESSAGETEXT+" = '"+ message +"'");
		
		String[] result = get_data(GETTER_MESSAGE_EXISTS);
		for(int i=0; i< result.length;i++){
			 if(result[i] != null){
			 return Integer.parseInt(result[i]);
			 	}
			 }
		return 0;
		
	}
	public boolean message_add(String message) {
		set_strsql("INSERT INTO "+DB_TABLE_MESSAGES+" ("+DB_COL_MESSAGETEXT+") VALUES ('"+ message +"')"); 
		
		return set_data();
	}

	public boolean user_add(String name){
		set_strsql("INSERT INTO "+DB_TABLE_USERNAME+" ("+DB_COL_USERNAME+") VALUES ('"+ name +"')"); 
				
		return set_data();
		
	}
	public boolean user_remove(String name){
		set_strsql("DELETE FROM "+DB_TABLE_USERNAME+" WHERE ("+DB_COL_USERNAME+") = ('"+ name +"')"); 
				
		return set_data();
		
	}
	
	public boolean set_data(){
		return execute_update();
	}
	@SuppressWarnings("null")
	public String[] get_data(String[] getter){
		ArrayList<String> result = new ArrayList<String>();
		execute_query();
		try {
			while(rs.next())
			{
				for(int y = 0;y < getter.length;y++){
					result.add(rs.getString(getter[y]));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close_result_set();
		String[] resultarray = new String[result.size()];
		for(int x = 0; x < result.size();x++){
			resultarray[x] = result.get(x);
		}
		return resultarray;
		
	}
	private boolean execute_update(){
		try {
			stmt.executeUpdate(strsql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	private void execute_query(){
		try
	    {
	    rs = stmt.executeQuery(strsql);
	    } catch(SQLException e)
	    {
	      e.printStackTrace();
	    }
		
	}
	private void close_result_set(){
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void set_strsql(String strsql){
		this.strsql = strsql;
	}
	public String get_strsql(){
		return this.strsql ;
	}
	
	
	protected void create_database(){
		
		strsql = "CREATE TABLE "+ DB_TABLE_USERNAME +" ("+ DB_COL_USERID +" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)PRIMARY KEY, "+ DB_COL_USERNAME +" VARCHAR(255))"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_USERDATA +" ("+ DB_COL_USERID +" INTEGER,"+ DB_COL_USERIP +" VARCHAR(255) ,FOREIGN KEY ("+ DB_COL_USERID +") REFERENCES "+ DB_TABLE_USERNAME +" ("+ DB_COL_USERID +"))"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_MESSAGES +" ("+ DB_COL_MESSAGEID +" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)PRIMARY KEY, "+ DB_COL_MESSAGETEXT +" LONGVARCHAR)"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_MESSAGEBOARD +" ("+ DB_COL_ACTIONID +" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)PRIMARY KEY,"+ DB_COL_SENTFROMUSERID +" INTEGER,"+ DB_COL_SENTTOUSERID +" INTEGER, "+ DB_COL_MESSAGEID +" INTEGER,"
					+ DB_COL_SENTTRUE +" BIT DEFAULT FALSE NOT NULL,"+ DB_COL_INCOMINGDATE +" TIMESTAMP,"
					+ "FOREIGN KEY ("+ DB_COL_SENTFROMUSERID +") REFERENCES "+ DB_TABLE_USERNAME +" ("+ DB_COL_USERID +"),"
					+ "FOREIGN KEY ("+ DB_COL_SENTTOUSERID +") REFERENCES "+ DB_TABLE_USERNAME +" ("+ DB_COL_USERID +"),"
					+ "FOREIGN KEY ("+ DB_COL_MESSAGEID +") REFERENCES "+ DB_TABLE_MESSAGES +" ("+ DB_COL_MESSAGEID +"))"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	protected void initialise_database() {
		 String newFolder = "/"+DB_FOLDER;
		 String extStorageDirectory = "";
		 try{
			 extStorageDirectory = Environment.getExternalStorageDirectory().toString();
		 }
		 catch(Exception e){
			 //e.printStackTrace();
		 }
	     
	     File myNewFolder = new File(extStorageDirectory + newFolder);
	     myNewFolder.mkdir();
		
		File path = myNewFolder; 
	    File file = new File(path, DB_NAME);
	    String filepath = file.getAbsolutePath();
	    try {
	    	dbVerbindung = DriverManager.getConnection("jdbc:hsqldb:file:"+filepath+";ifexists=true", "SA", "");
	    	dbVerbindung.setAutoCommit(true);
	    	stmt = dbVerbindung.createStatement();
	    } catch (SQLException e) {
	    	System.out.println(e.getErrorCode());
	    	if(e.getErrorCode()==-465){
	    		try {
					dbVerbindung = DriverManager.getConnection("jdbc:hsqldb:file:"+file.getAbsolutePath()+"", "SA", "");
					dbVerbindung.setAutoCommit(true);
					stmt = dbVerbindung.createStatement();
					create_database();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		    }
	    }
	    Runtime.getRuntime().addShutdownHook(new Thread() {
	        public void run() {
	            try {
	            	if (rs != null && !rs.isClosed()){
	            		rs.close();
	            	}
	                if (!dbVerbindung.isClosed()) {
	                	dbVerbindung.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

	
	
}
