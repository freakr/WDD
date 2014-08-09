package freakrware.wdd.server.resources;
import java.util.prefs.Preferences;


public class Server_Setup {

	private Preferences prefs = Preferences.userRoot().node(this.getClass().getName());  
	
	public void set_Parameter(String id,String value) {
		//prefs = Preferences.userRoot().node(this.getClass().getName());
		prefs.put(id,value );
		
		}
	public String get_Parameter(String id) {
		//prefs = Preferences.userRoot().node(this.getClass().getName());
		return prefs.get(id,"");
		}
}
