package org.snanny.yellowpages;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;




public class MysqlToJSON {
	
	
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = Config.getSetting("db_URL");   
   //  Database credentials
   static final String USER = Config.getSetting("db_User");
   static final String PASS = Config.getSetting("db_Pass");
   static final String Path= Config.getSetting("modelsDestPath");
   static final String imageHost= Config.getSetting("imagesHost");
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   
   String[]SensorType = {"DATA_ADCP","DATA_CO2_ANALYSER","DATA_CONDUCTIVITY","DATA_CTD","DATA_CURRENT_METER","DATA_DEPTH","DATA_DO_SENSOR","DATA_FLOW_METER","DATA_FLUOROMETER","DATA_GEOPHONE","DATA_HYDROPHONE","DATA_MAGNETOMETER","DATA_MULTIPARAMETER","DATA_PAH","DATA_PAR_SENSOR","DATA_PARTICLE_SIZER","DATA_PH_SENSOR","DATA_PRESSURE_SENSOR","DATA_REDOX","DATA_SALINOMETER","DATA_SEDIMENT_TRAP","DATA_TEMPERATURE","DATA_TILTMETER","DATA_TURBIDITY","DATA_WATER_SAMPLER"};
   String[]HardwareType= {"DATA_ACOUSTIC_MODEM","DATA_ACOUSTIC_RELEASE","DATA_CAMERA","DATA_CONNECTOR","DATA_LOGGER","DATA_FLOAT","DATA_HOUSING","DATA_LASER","DATA_LIGHT","DATA_MOORING_SYSTEM","DATA_POSITIONING_EQUIPMENT","DATA_UNDERWATER_BATTERY","DATA_UNDERWATER_CABLE","DATA_UNDERWATER_SWITCH" };
   String [][]ModelType= new String[SensorType.length+HardwareType.length][2];
   for( int i=0;i<SensorType.length;i++)
   {
	   ModelType[i][0]=SensorType[i];
	   ModelType[i][1]="sensor";
   }
   int index=0;
   for( int i=SensorType.length;i<SensorType.length+HardwareType.length;i++)
   {
	   ModelType[i][0]=HardwareType[index];
	   ModelType[i][1]="hardware";
	   index++;
   }
   try{
       //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      for ( int j=0 ; j<ModelType.length;j++)
    		  {
      String sql;
      
      sql = "select "+ModelType[j][0]+".*, "+ModelType[j][1]+".model , manufacturer.name as manufacturer, "+ModelType[j][1]+".description, "+ModelType[j][1]+".createdby, user.name from "+ModelType[j][0]+", "+ModelType[j][1]+" LEFT OUTER JOIN user on "+ModelType[j][1]+".createdby=user.email , manufacturer where "+ModelType[j][0]+"."+ModelType[j][1]+"_id="+ModelType[j][1]+".id and manufacturer.id="+ModelType[j][1]+".manufacturer ";
     
      ResultSet rs = stmt.executeQuery(sql);

     System.out.println(sql);
    
      //STEP 5: Extract data from result set
      while(rs.next()){
    	  String modelName = rs.getString("model");
    	  modelName=modelName.replace(" ","_");
    	  modelName=modelName.replaceAll("/","");
    	  modelName=modelName.replace("-","");
    	  modelName=modelName.replace(".","");
    	  modelName=modelName.replace("(","");
    	  modelName=modelName.replace(")","");
    	  modelName=modelName.replace("™",""); 
    	  modelName=modelName.replace("+","");
    	  modelName=modelName.replace("&","");
    	  modelName=modelName.replace(",","");
    	  modelName=modelName.replace("\"","");
    	  modelName=modelName.replace("®","");
    	  modelName=modelName.toLowerCase();
    	  
    	 
    	
    	  
    	  
    	//Sensor Model JSON 
    	  JsonObject sensorModel = new JsonObject();
         
       // Retreive Metadata
         ResultSetMetaData m=rs.getMetaData();
         // Instanciate New json array for Identifier Contact
         JsonArray identifierDatasets = new JsonArray();
        // Instanciate New json array for Classifier Contact
        
         JsonArray classifierDatasets = new JsonArray();
         JsonArray contactDatasets = new JsonArray();
         // Instanciate New json Object for Custom, Attributes and images
         JsonObject custom = new JsonObject();
         JsonObject attr = new JsonObject();
       //Add Name         
         JsonObject name = new JsonObject();          
         name.addProperty("name",rs.getString("model"));
       
       
         
         
         attr.add("text", name);
         
        //Documentation list 
         JsonObject documentation = new JsonObject();
         String link=imageHost+modelName+".png";
         documentation.addProperty("link", link);
         documentation.addProperty("name", modelName+".png");
         sensorModel.add("documentation", documentation);
         
         JsonObject image = new JsonObject();
         // Add Image properties
         image.addProperty("width",50);
         image.addProperty("height",50);
      /*  png to  base64
         InputStream url = new FileInputStream("C:/wamp/www/MysqlToJS/models/"+modelName+".png");
		byte[] imageBytes = IOUtils.toByteArray(url);
         String base64 = Base64.encodeBase64String(imageBytes);
         System.out.println(base64);
         image.addProperty("xlink:href","data:image/png;base64,"+base64);*/
         
         image.addProperty("xlink:href","images/models/"+modelName+".png");
         attr.add("image", image);
         // Add Attributes in Json 
         sensorModel.add("attrs", attr);
         //add Type
         
         sensorModel.addProperty("type", "basic."+ModelType[j][0].substring(5));
         // Add Description
         String description = rs.getString("description").replace("&#13;", "");
         
         description=description.replace("&#10;","");
         sensorModel.addProperty("description", description);
         // Add Contact
         JsonObject contactDataset = new JsonObject();  
         
         contactDataset.addProperty("role","pointOfContact");  
         contactDataset.addProperty("email",rs.getString("createdby"));
         if(rs.getString("name")==null)
         contactDataset.addProperty("name","");	 
         else
         contactDataset.addProperty("name",rs.getString("name"));
         
         contactDatasets.add(contactDataset);
         
         // Add system code
         //// TO BE DONE ////
         JsonObject code = new JsonObject();          
         code.addProperty("name", "code");  
         String codeStr = ModelType[j][1]+""+ModelType[j][0]+"_"+rs.getString(ModelType[j][1]+"_id");
         code.addProperty("value", codeStr);
         code.addProperty("URI", "");
         code.addProperty("Ref", "modelData");
         
         identifierDatasets.add(code);
         
         //Add manufacturer
         JsonObject model = new JsonObject();          
         model.addProperty("name", "model");
         model.addProperty("value", rs.getString("model"));
         model.addProperty("URI", "");
         model.addProperty("Ref", "modelData");
         
         classifierDatasets.add(model);
         
         
        JsonObject manufacturer = new JsonObject();          
        manufacturer.addProperty("name", "manufacturer");
        manufacturer.addProperty("value", rs.getString("manufacturer"));
        manufacturer.addProperty("URI", "");
        manufacturer.addProperty("Ref", "modelData");
        manufacturer.addProperty("definition", "http://www.ifremer.fr/tematres/vocab/index.php?tema=52");
        manufacturer.addProperty("codespace", "http://www.ifremer.fr/tematres/vocab/xml.php?skosTema=40");
        
        classifierDatasets.add(manufacturer);
        // Add Data and meta data of Classifier list
         for(int i=2;i<m.getColumnCount()-4;i++)
         {
        	
             JsonObject classifierDataset = new JsonObject();
            
             classifierDataset.addProperty("name", m.getColumnName(i));
            
             classifierDataset.addProperty("value", rs.getString(i));
             classifierDataset.addProperty("URI", "");
             classifierDataset.addProperty("Ref", "modelData");
             
     if(m.getColumnName(i).toLowerCase().equals("release load"))
    	 classifierDataset.addProperty("definition", "http://www.ifremer.fr/tematres/vocab/index.php?tema=155");
     if(m.getColumnName(i).toLowerCase().equals("safe working load"))
    	 classifierDataset.addProperty("definition", "http://www.ifremer.fr/tematres/vocab/index.php?tema=156");
     if(m.getColumnName(i).toLowerCase().equals("max load"))
    	 classifierDataset.addProperty("definition", "http://www.ifremer.fr/tematres/vocab/index.php?tema=157");
     if(m.getColumnName(i).toLowerCase().equals("depth rating"))
    	 classifierDataset.addProperty("definition", "http://www.ifremer.fr/tematres/vocab/index.php?tema=158");
     if(m.getColumnName(i).toLowerCase().equals("frequency range"))
    	 classifierDataset.addProperty("definition", "http://www.ifremer.fr/tematres/vocab/index.php?tema=159");
     if(m.getColumnName(i).toLowerCase().equals("battery life"))
    	 classifierDataset.addProperty("definition", "http://www.ifremer.fr/tematres/vocab/index.php?tema=160");
             classifierDatasets.add(classifierDataset);
            
      
         }
       
         
    	 //UUID
         sensorModel.addProperty("uuidModel",UUID.nameUUIDFromBytes(rs.getString("model").getBytes()).toString());
         
         // Add identifier, classifier and contact in JSON Object SensorModel                
        custom.add("identifier",identifierDatasets);
        custom.add("classifier",classifierDatasets);
        custom.add("contactMetaData",contactDatasets);        
        sensorModel.add("custom", custom);
       
        
        // Write each json Data in models folder
         try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                 new FileOutputStream(Path+ModelType[j][1]+""+ModelType[j][0]+"_"+rs.getString(ModelType[j][1]+"_id")+".json"), "utf-8"))) {
      writer.write(sensorModel.toString());
   }
      
       
        
         
      }
    		  
      //STEP 6: Clean-up environment
      rs.close();
    		  }
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   
   System.out.println("Goodbye! Destination folder : "+Path);
}//end main
}//end FirstExample


