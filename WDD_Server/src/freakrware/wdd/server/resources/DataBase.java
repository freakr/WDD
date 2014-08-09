
package freakrware.wdd.server.resources;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private DataBase(){
		
		initialisiern_datenbank();
		
	}

	public void add_data(){
		execute_update();
	}
	public void get_data(){
		execute_query();
		try {
			while(rs.next())
			{
				//TODO routine for get data
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close_result_set();
		
	}
	private void execute_update(){
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		strsql = "CREATE TABLE Serien (SerienID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)PRIMARY KEY, Serienname VARCHAR(255))"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE Staffeln (StaffelnID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)PRIMARY KEY, StaffelNr INTEGER)"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE Episoden (EpisodenID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)PRIMARY KEY, EpisodeNr INTEGER)"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE Vorhanden (VorhandenID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)PRIMARY KEY, Serie INTEGER, Staffel INTEGER,Episode INTEGER,"
					+ "Vorhanden BIT,Gesehen BIT DEFAULT FALSE NOT NULL,Datum TIMESTAMP,"
					+ "FOREIGN KEY (Serie) REFERENCES Serien (SerienID),"
					+ "FOREIGN KEY (Staffel) REFERENCES Staffeln (StaffelnID),"
					+ "FOREIGN KEY (Episode) REFERENCES Episoden (EpisodenID))"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	protected void initialisiern_datenbank() {
		 String newFolder = "/"+DB_FOLDER;
		 String extStorageDirectory = "";
		 if(Environment.getExternalStorageDirectory()!= null){
	     extStorageDirectory = Environment.getExternalStorageDirectory().toString();
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
	            	if (!rs.isClosed()){
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
