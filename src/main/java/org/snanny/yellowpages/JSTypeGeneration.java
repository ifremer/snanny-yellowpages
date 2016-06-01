package org.snanny.yellowpages;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URISyntaxException;
import org.apache.commons.io.IOUtils;

public class JSTypeGeneration {
	 private static String[]ModelType = {"DATA_ADCP","DATA_CO2_ANALYSER","DATA_CONDUCTIVITY","DATA_CTD","DATA_CURRENT_METER","DATA_DEPTH","DATA_DO_SENSOR","DATA_FLOW_METER","DATA_FLUOROMETER","DATA_GEOPHONE","DATA_HYDROPHONE","DATA_MAGNETOMETER","DATA_MULTIPARAMETER","DATA_PAH","DATA_PAR_SENSOR","DATA_PARTICLE_SIZER","DATA_PH_SENSOR","DATA_PRESSURE_SENSOR","DATA_REDOX","DATA_SALINOMETER","DATA_SEDIMENT_TRAP","DATA_TEMPERATURE","DATA_TILTMETER","DATA_TURBIDITY","DATA_WATER_SAMPLER", "DATA_ACOUSTIC_MODEM","DATA_ACOUSTIC_RELEASE","DATA_CAMERA","DATA_CONNECTOR","DATA_LOGGER","DATA_FLOAT","DATA_HOUSING","DATA_LASER","DATA_LIGHT","DATA_MOORING_SYSTEM","DATA_POSITIONING_EQUIPMENT","DATA_UNDERWATER_BATTERY","DATA_UNDERWATER_CABLE","DATA_UNDERWATER_SWITCH" };
	 
	 private static String Path=Config.getSetting("typesJSDestPath");
	
	

	//JS types creation from Template TypeModel.js 
	private static void jsTypesGeneration() throws IOException, URISyntaxException{
		
		Charset charset = StandardCharsets.UTF_8;
		for(int i=0;i<ModelType.length;i++){
					InputStream inputStream = new JSTypeGeneration().getClass().getResourceAsStream("TypeModel.js");

StringWriter writer = new StringWriter();
			IOUtils.copy(inputStream, writer, charset);
			String content = writer.toString();
					
			Path newType = Paths.get(Path+ModelType[i].substring(5)+".js");
			content = content.replaceAll("TypeName", ModelType[i].substring(5));
			Files.write(newType, content.getBytes(charset));
			
		}
	}
	
	 //include generation for Index.html of SensorNannyDraw
	private static void includesGeneration()
	{
		try(PrintWriter output = new PrintWriter(new FileWriter("index.txt",true))) 
		{
			for(int i=0;i<ModelType.length;i++){
		    output.printf("%s\r\n", "<script src=\"./javascript/types/"+ModelType[i].substring(5)+".js\"></script>");
		}
		}
		catch (Exception e) {}
		
	}
	//generation Stencil groups for Stencil.js of SensorNannyDraw
	private static void stencilGroupsGeneration(){
		
		try(PrintWriter output = new PrintWriter(new FileWriter("stencilGroups.txt",true))) 
		{
			for(int i=0;i<ModelType.length;i++){
				int index = i+4;
		    output.printf("%s\r\n", ModelType[i].substring(5)+": { index: "+index+", label: 'EMSO_"+ModelType[i].substring(5)+"' },");
		}
		} 
		catch (Exception e) {}		
	}
	//Generation Stencil shapes for Stencil.js of SensorNannyDraw
	private static void stencilShapesGeneration(){		
		
		try(PrintWriter output = new PrintWriter(new FileWriter("stencilShapes.txt",true))) 
		{
			for(int i=0;i<ModelType.length;i++){
				
		    output.printf("%s\r\n",ModelType[i].substring(5)+": [],");
		}
		} 
		catch (Exception e) {}
		
	}
	 
	public static void main(String[] args) throws IOException , URISyntaxException{
		// TODO Auto-generated method stub
		
		
		jsTypesGeneration();
		includesGeneration();
		stencilGroupsGeneration();
		stencilShapesGeneration();
		
		System.out.println("Finished Destination folder : "+Path);
	}


}
