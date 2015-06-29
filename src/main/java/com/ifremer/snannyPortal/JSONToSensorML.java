
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONToSensorML {
	static String pathModels=Config.getSetting("modelsSrcPath");
	static String pathSensorML=Config.getSetting("sensorMLDestPath");
	public static Map<String, Object> toMap(JSONObject object) throws JSONException
	{
	    Map<String, Object> map = new HashMap();
	    Iterator keys = object.keys();
	    while (keys.hasNext())
	    {
	        String key = (String) keys.next();
	        map.put(key, fromJson(object.get(key)));
	    }
	    return map;
	}

	public static List toList(JSONArray array) throws JSONException
	{
	    List list = new ArrayList();
	    for (int i = 0; i < array.length(); i++)
	    {
	        list.add(fromJson(array.get(i)));
	    }
	    return list;
	}

	private static Object fromJson(Object json) throws JSONException
	{
	    if (json instanceof JSONObject)
	    {
	        return toMap((JSONObject) json);
	    } else if (json instanceof JSONArray)
	    {
	        return toList((JSONArray) json);
	    } else
	    {
	        return json;
	    }
	}
	

	 static String readFile(String path, Charset encoding) 
   		  throws IOException 
   		{
   		  byte[] encoded = Files.readAllBytes(Paths.get(path));
   		  return new String(encoded, encoding);
   		}

  

 
  public static void main(String[] args) throws IOException {
	   
	  Handlebars handlebars = new Handlebars();

	  Template template = handlebars.compile("template.handlebars");
	        
	  File[] files = new File(pathModels).listFiles();
	        
	      
	        for (File file : files) {
	        	
	        
	        	OutputStream outputStream = new FileOutputStream(pathSensorML+FilenameUtils.removeExtension(file.getName()) +".xml");
	        	Writer       writer       = new OutputStreamWriter(outputStream);
	        	String content = readFile(file.getPath(), StandardCharsets.UTF_8);
	        	Map<String, Object> modelMap = JSONToSensorML.toMap(new JSONObject(content));	
	        	writer.append(template.apply(modelMap));
	        	
	        	writer.flush();
	        	writer.close();
	        	
	        }
	        System.out.println("finished ==> Destination Folder : "+pathSensorML);
	    
	  }
}