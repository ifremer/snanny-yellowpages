/*import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Jackson2Helper;
import com.github.jknack.handlebars.JsonNodeValueResolver;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;


public class JSONtoSensorML {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 String json = "{\"attrs\":{\"image\":{\"width\":50,\"height\":50,\"xlink:href\":\"images/models/hamnode_modular_hydro_acoustic_modem.png\"}},\"type\":\"ACOUSTIC_MODEM\",\"description\":\"HAM.NODE, our hydro acoustic modem, is a powerful and reliable underwater communication system. It is designed the meet the requirements of research and exploration, established in various subsea communication projects.\\r\\n\\r\\nBy flexible tasks modulation and coding, a variety of available transducers in different frequency bands, high maximum transmit power, power efficiency and flexible interfaces a powerful solution for demanding subsea communication is ensured. An applicable communication allows direct connection of wide variety sensors, mechanical robustness and superior corrosion resistance of our synthetic pressure housings. The overall performance exceeds those of competing systems and enables subsea communications not solvable so far.\\r\\n\\r\\nOur medium and high load HAM.R release modules (HAM.ISBD and HAM.IRDS Iridium Satellite Communication Modules) and in conjunction with our battery housings are a further step forward to enable a reliable communication possibility from the seafloor to the desktop.\\r\\n\\r\\nAn extensive international benchmarking exposed develogic to be the sole provider of hydro acoustic communication solutions for the German Indonesian Tsunami Early Warning system (GITEWS). \\r\\n\\r\\nFurthermore develogic deployed multiple HAM.NODE long range systems in the Fram Strait (between Svalbard and Greenland) as a part of the CAMOCLES project. Those transmit ocean current data between moorings in distances up to 30,000m.\\r\\n\\r\\nAs part of the GITEWS project, In November 2007 a PACT system had been deployed near the Canary Islands. This project proved a transmission reliability rate of more than 98% during the entire 6-months-deployment period under onerous weather conditions across distances of abt. 3,900m. The system is equipped with a HAM.NODE OEM modem which communicates with the surface buoy. The HAM.ISDB iridium Satellite Communication Module transmits the bottom sensor data to the Alfred-Wegener-Institute for Polar and Marine research in Bremerhaven.\\r\\n\\r\\nSystem Details\\r\\n\\r\\nHAM.NODE provides as communication layer highly adaptive to the present acoustic channel. To challenge vertical configurations or channels with limited multi-path complexity, our system offers high transmission speeds with OFDM-mDPSK modulation schemes. Those are available with or without decision feedback equalizer. To demand long range configurations in shallow water or within acoustic channels, an n-mFSK modulation scheme is available.\\r\\n\\r\\nSpecial features such as convolutional error correction with coding rates, Doppler compensation and data compression are included.\\r\\nOur multi node capable communication protocol (featuring handshake and automatic retransmission) ensures an absolute reliable data transmission (provided transmission is possible at all). The generated user data are available at the receiving unit afterwards without haven been verified before.\\r\\nThe default system configuration contains two serial ports capable to work in RS422 or RS232 mode (with or without handshake) at baud rates up to 230kbaud. Additional serial ports and a CAN-bus module are available.\\r\\nMechanical Configuration\\r\\n\\r\\nHAM.NODE systems are available with two standard housing configurations:\\r\\n\\r\\nShallow Water Modular Composite Housing (SW.MCH)\\r\\n\\r\\n- tested to 750m\\r\\n- Outer shell is made of reinforced synthetics with high strength hard coated aluminium inlays in the housing caps ( not in contact with seawater)\\r\\n- Cap plates are made of synthetics, easily exchangeable\\r\\n- Inner diameter: 110mm\\r\\n- Inner useable length. 480mm\\r\\n\\r\\nDeep Water Modular Housing (DW.MCH)\\r\\n\\r\\n- tested to 6,000m\\r\\n- Outer shell made of fiber reinforced synthetics with high strength hard coating aluminium inlays in the housing caps and the pressure tube ( not in contact with seawater)\\r\\n- Housing cap plates are made of titanium and are easily exchangeable\\r\\n- Inner diameter: 110mm\\r\\n- Inner useable length: 480mm\\r\\n\\r\\nBoth housings are long-term corrosion proof. No metal will be in contact with seawater by using the shallow water housings. Deep water housings are made of titanium and composite synthetics. Our housings offer superior compatibility to instrument platforms and moorings frames with stainless steel or titanium pars. The synthetic outer shell offers a reliable protection to galvanic corrosion.\\r\\n\\r\\nTransducer base plates are available in Titanium and synthetics.  Other materials are disposable on request.\\r\\n\\r\\nBesides the modem electronics, housings are able to carry a 28 cell (D-size) battery pack. Standard battery options are available as well. They include 21 cells NiMh 9.5Ah rechargeable, 28 cells of Lio-SOCl2 or 28 cells of Alkaline.\\r\\n\\r\\nWe furthermore offer modems without housings, to be integrated into Vitrovex housings upon request. Our pressure housings are available as separate units.\\r\\n\\r\\nUser interface and extended functionality\\r\\n\\r\\nHAM.NODE offers a user interface with an extensive functionality. It is equipped with a user accessible FAT32 file system on a SDHC card (exchangeable, also used for system configuration and logging purposes). Furthermore it includes extensive diagnostic functions and access to the data of integrates sensors (3-axis acceleration, temperatures, optionally housing pressure and humidity, voltages etc.).\\r\\n\\r\\nThe user interface provides three data transmission modes. If you chose the fully transparent mode, all data sent via host interface will be transmitted to the remote slide. The advanced telegram mode allows complete control of data transmission. File based data transmission functionalities such as recording user data (e.g. from sensors) in files of the integrated SDHC card. Afterwards those data can be either transmitted at a prescheduled time (triggered by the local host system) or they just can be retrieved by the remote system.\\r\\n\\r\\nAn optional high precision time base, algorithmically compensated to provide less than 0.03ppm drift per year, is available.\\r\\n\\r\\nThe factory customizable application layer allows convenient interfacing to 3rd sensor party systems. There is no need for extra interface boxes. Examples contain Anderaa current meters, paroscientific pressure sensors, RBR data loggers and others. \\r\\n\\r\\nThere is also a telecommand software extension available to enable interaction of HAM.NODE systems with selected 3rd party acoustic releases without the need of having a telecommand deck unit.\\r\\n\\r\\nElectrical characteristics\\r\\n\\r\\nThe HAM.NODE system requires a supply voltage between 5V and 33V. The minimal supply voltage for active data transmission is 12V.\\r\\n\\r\\nDepending on the chosen transducer option and the selected output level, the maximum transmit power (electrical output into the transducer) is 200W by using the standard power amplifier. Usual medium range power settings are 30W up to 80W.\\r\\n\\r\\nMean time between servicing can be 60 months depending on the installed battery capacity.\\r\\n\\r\\nAcoustic and Transmission characteristics\\r\\n\\r\\nHAM.NODE is available with three standard transducer configurations\\r\\n\\r\\nMF omnidirectional:\\r\\ncarrier bad 8-13 kHz , 240\u00c2\u00b0 -3dB beam width\\r\\nsuitable for low  and medium speed transmissions in flexible channels\\r\\n\\r\\nMF directional:\\r\\ncarrier bad 11-20 kHz , 65\u00c2\u00b0 -3dB beam width\\r\\nsuitable for high speed transmission in vertical channels for more than 6,000m\\r\\n\\r\\nMHF directional:\\r\\ncarrier bad 17-29 kHz , 60\u00c2\u00b0 -3dB beam width\\r\\nsuitable for high speed transmission in vertical channels up to 4,000m\\r\\n\\r\\nExamples for net user data rates range from 145bps at 30.,000m horizontal (n-mFSK) over abt. 3,400bps at 6,000 vertical (OFDM-mDPSK) to >7,000bps at 1,95m vertical (OFDM-mDPSK)\",\"custom\":{\"classifier\":[{\"name\":\"model\",\"URI\":\"HAM.NODE Modular Hydro Acoustic Modem\"},{\"name\":\"manufacturer\",\"URI\":\"Develogic GmbH\"},{\"name\":\"name\",\"URI\":\"Develogic GmbH\"}],\"contact\":[{\"email\":\"Jasmin.Asmussen@develogic.de\"}]}}";
	        JsonNode jsonNode = new ObjectMapper().readValue(json, JsonNode.class);
	        Handlebars handlebars = new Handlebars();
	        handlebars.registerHelper("json", Jackson2Helper.INSTANCE);

	        Context context = Context
	            .newBuilder(jsonNode)
	            .resolver(JsonNodeValueResolver.INSTANCE,
	                    JavaBeanValueResolver.INSTANCE,
	                    FieldValueResolver.INSTANCE,
	                    MapValueResolver.INSTANCE,
	                    MethodValueResolver.INSTANCE
	            )
	            .build();
	        Template template = handlebars.compileInline("Hello  {{#custom}} {{#classifier}} {{/classifier}} {{/custom}} !");
	        System.out.println(template.apply(context));
		/*Handlebars handlebars = new Handlebars();

		Template template = handlebars.compile("model");

		System.out.println(template.apply("Handlebars.java"));
	}

}
*/


import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONMustache {
	static String pathModels="C:/wamp/www/webgraphiceditorDemo/models";
	static String pathSensorML="C:/wamp/www/MysqlToJS/sensorML/";
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
	   
	    MustacheFactory mf = new DefaultMustacheFactory();
	    Mustache mustache = mf.compile("template.mustache");
	    
	
	    
	        File[] files = new File(pathModels).listFiles();
	        
	       System.out.println(files.length);
	        for (File file : files) {
	        	
	        
	        	OutputStream outputStream = new FileOutputStream(pathSensorML+FilenameUtils.removeExtension(file.getName()) +".xml");
	        	Writer       writer       = new OutputStreamWriter(outputStream);
	        	String content = readFile(file.getPath(), StandardCharsets.UTF_8);
	        	 Map<String, Object> modelMap = JSONMustache.toMap(new JSONObject(content));
	        	
	        	System.out.println(file.getName());
	        	
	        	 mustache.execute(writer, modelMap);
	           
	        
	        	writer.flush();
	        	
	        }
	    
	  }
}