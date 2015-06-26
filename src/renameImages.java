
import java.io.File;
public class renameImages {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		  String myDirectoryPath = "C:/wamp/www/webgraphiceditorDemo/images/old";
		File dir = new File(myDirectoryPath );
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {

	
String name=child.getName();
String[] namesplitted= name.split("_");
String newName="";
for( int i=namesplitted.length-2;i>=0;i--)
{
	newName=namesplitted[i]+"_"+newName;
}
if (newName.length() > 0 && newName.charAt(newName.length()-1)=='_') {
	newName = newName.substring(0, newName.length()-1);
  }
  

newName=newName+".png";
child.renameTo(new File("C:/wamp/www/webgraphiceditorDemo/images/modelsEdited/"+newName));
System.out.println(newName);
		    
		    }
		  } else {
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		  }
	}

}
 