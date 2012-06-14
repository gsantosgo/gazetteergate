package es.uem.gazetteer.geonames.main;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.io.Files;

import es.uem.gazetteer.geonames.lineprocessor.GeonamesESLineProcessor;

/**
 * Main class (Geonames)
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es) 
 */
public class MainES {		
 	
	final static Hashtable<Integer, String> hashAlternateNames = new Hashtable<Integer, String>(30*1000);   
			
	/**
	 * 
	 * Main program to generate Location files for GATE 
	 * using Geonames (http://download.geonames.org/export/dump/) 
	 *      
	 * @param args
	 */
	public static void main(String[] args) throws LangDetectException {
		String langProfilesDirectoryName = "src/main/resources/profiles"; 
		String inputFileNamePath = "/home/gsantos/geonames/data/ES.txt";
		String outputDirectoryNamePath = "src/main/resources/output";
				
		File langProfilesDirectory = new File(langProfilesDirectoryName);		
		// Load language profiles  
		DetectorFactory.loadProfile(langProfilesDirectory);
		if (!DetectorFactory.getLangList().isEmpty()) {
			System.out.println("Loaded languages list : " + DetectorFactory.getLangList().size());
		} 
													
		Preconditions.checkNotNull(inputFileNamePath, "Input filename allCountries.text (Geonames) should NOT be NULL");
		Preconditions.checkNotNull(outputDirectoryNamePath, "Output directory should NOT be NULL.");
		 
		File inputFile = new File(inputFileNamePath); 
		File outputDirectory = new File(outputDirectoryNamePath);
			
		Preconditions.checkArgument(inputFile.exists(), "File does not exist: %s", inputFile);		
		Preconditions.checkArgument(outputDirectory.exists(), "Directory does not exist: %s", outputDirectory);		 		
		

		// AllCountries 
		System.out.println("Starting process ....");
		System.out.println("> Waiting....");
		Stopwatch stopWatch1 = new Stopwatch();
		stopWatch1.start();
		String resultado1 = ""; 
		try {
				resultado1 = Files.readLines(inputFile, Charsets.UTF_8, new GeonamesESLineProcessor(outputDirectoryNamePath));
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
			
		System.out.println(String.format(
					"Processed registers %s.",resultado1));	
		System.out.println(String.format(
					"Complete process. Elapsed time %d min, %d sec.",
					stopWatch1.elapsedTime(TimeUnit.MINUTES),
					stopWatch1.elapsedTime(TimeUnit.SECONDS)));
		System.out.println("");		

	}	
			
}
