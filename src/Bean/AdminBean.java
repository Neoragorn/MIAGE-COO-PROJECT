package Bean;

public class AdminBean extends UserBean{
	
	public AdminBean() {
		
	}

	 public static AdminBean inst;

	    static public AdminBean getInstance() {
	        if (inst == null) {
	            inst = new AdminBean();
	        }
	        return inst;
	    }
	    
	 
}
