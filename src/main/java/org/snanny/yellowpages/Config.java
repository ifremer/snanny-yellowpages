package org.snanny.yellowpages;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

	
	 private static final String DEFAULT_CONFIG_FILE = "config.properties";
		
	 private static final Properties properties = new Properties();

	    static {
	        try {
	        	String finalConfig = DEFAULT_CONFIG_FILE;
	        	String configFile = System.getProperty("configFile");
				System.out.println("here"+configFile);
	        	if(configFile != null) {
	        		finalConfig = configFile;
	        		InputStream is = new FileInputStream(finalConfig);
	        		properties.load(is);
	        	}
	        	else
	        	{
	            ClassLoader loader = Thread.currentThread().getContextClassLoader();
	            properties.load(loader.getResourceAsStream(finalConfig));
	        	}
	        } catch (IOException e) {
	            throw new ExceptionInInitializerError(e);
	        }
	    }

	    public static String getSetting(String key) {
	        return properties.getProperty(key);
	    }

	
	
	
}

