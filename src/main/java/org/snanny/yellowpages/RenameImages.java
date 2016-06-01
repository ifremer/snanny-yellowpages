package org.snanny.yellowpages;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public class RenameImages {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		  String myDirectoryPath =Config.getSetting("imagesSrcPath");
		File dir = new File(myDirectoryPath );
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {

	
			String name=child.getName();
			String[] namesplitted= name.split("_");
			String newName="";
			for( int i=namesplitted.length-2;i>=0;i--){
				newName=namesplitted[i]+"_"+newName;
			}
			if (newName.length() > 0 && newName.charAt(newName.length()-1)=='_') {
				newName = newName.substring(0, newName.length()-1);
			}
			  
			newName=newName+".png";
			File newFile = new File(Config.getSetting("imagesDestPath")+newName);
			try{
				Files.move(child.toPath(), newFile.toPath());
				System.out.println(newFile.getAbsolutePath());
			//child.renameTo(newFile);
			} catch (IOException ioe){
				System.out.println(ioe.getMessage());
			}

					    
					    }
		  } 
	}

}
 
