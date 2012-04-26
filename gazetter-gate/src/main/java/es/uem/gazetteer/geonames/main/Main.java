package es.uem.gazetteer.geonames.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.io.Files;

import es.uem.gazetteer.jrc.lineprocessor.JRCNamesLineProcessor;

/**
 * Main class (Geonames)
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es) 
 */
public class Main {
	
	final static String LANGUAGE = "es"; 
	final static String EXTENSION = ".lst";  
	
	@SuppressWarnings("serial")
	final static Map<String, String> filters = new HashMap<String, String>() { 
	{		
	    put("A", "A_Country_State_Region");
	    put("H", "H_Stream_Lake");
	    put("L", "L_Parks_Area");
	    put("P", "P_City_Village");
	    put("R", "R_Road_RailRoad");
	    put("S", "S_Spot_Building_Farm");
	    put("T", "T_Mountain_Hill_Rock");
	    put("U", "U_Undersea");
	    put("V", "V_Forest_Heath");	    	    
	}};	
	
	/**
	 * 
	 * Main program to generate Person, Organization files for GATE 
	 * using JRCNames Entities sources (http://optima.jrc.it/data/entities.gzip) 
	 *      
	 * @param args
	 */
	public static void main(String[] args) {		
		String inputFileNamePath = "c:\\geonames\\allCountries.txt";
		String outputDirectoryNamePath = "src/main/resources";
		
		
		Preconditions.checkNotNull(inputFileNamePath, "Input filename should NOT be NULL");
		Preconditions.checkNotNull(outputDirectoryNamePath, "Output directory should NOT be NULL.");
		
		File inputFile = new File(inputFileNamePath); 
		File outputDirectory = new File(outputDirectoryNamePath);
			
		Preconditions.checkArgument(inputFile.exists(), "File does not exist: %s", inputFile);		
		Preconditions.checkArgument(outputDirectory.exists(), "Directory does not exist: %s", outputDirectory);		 
		
				
		Set<String> filterSet = filters.keySet();
		Iterator<String> iterator = filterSet.iterator();
		String filter = ""; 
		while (iterator.hasNext()) {
			 filter = iterator.next();					
			System.out.println(String.format("Starting process for filter %s ....", filter));
			System.out.println("> Waiting....");
			Stopwatch stopWatch = new Stopwatch();
			stopWatch.start();
						
			String resultado = "";
			try {
				File outputFile = new File(outputDirectoryNamePath + File.separator + filters.get(filter) + EXTENSION);
				// if file exists then remove it				
				if (outputFile.exists()) outputFile.delete();				
				resultado = Files.readLines(inputFile, Charsets.UTF_8,
						new JRCNamesLineProcessor(filter, outputFile));
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			
			System.out.println(String.format(
					"For filter %s, processed registers %s ", filter, resultado));	
			System.out.println(String.format(
					"Complete process. Elapsed time %d min, %d sec.",
					stopWatch.elapsedTime(TimeUnit.MINUTES),
					stopWatch.elapsedTime(TimeUnit.SECONDS)));
			System.out.println("");		
		} 
	}	
			
}
