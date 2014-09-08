package freakrware.wdd.server.resources;

public interface Standards_interface {

	public class Standards{
		
		public void wait(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	}
	
	
}
