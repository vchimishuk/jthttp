import java.util.HashMap;

class Configurations {
    private volatile static Configurations instance;
 
    private static HashMap<String, Object> configs = new HashMap<String, Object>();
    
    static {
    	configs.put("version", "Jthttp v0.0");
    	configs.put("docRoot", "/");
    	configs.put("port", new Integer(8080));
    	configs.put("threadsCount", new Integer(1));
    }
    
    private Configurations() {
    	
    }
 
    public static Configurations getInstance() {
        synchronized (Configurations.class) {
            if (instance == null) {
                instance = new Configurations();
            }
        }
        
        return instance;
    }
    
    public String getString(String name) {
    	return (String) configs.get(name);
    }
    
    public int getInt(String name) {
    	return ((Integer) configs.get(name)).intValue();
    }
}
