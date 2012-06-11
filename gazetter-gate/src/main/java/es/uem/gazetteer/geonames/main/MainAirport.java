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

import es.uem.gazetteer.geonames.lineprocessor.GeonamesAirportAllCountriesLineProcessor;
import es.uem.gazetteer.geonames.lineprocessor.GeonamesAlternateNamesLineProcessor;

/**
 * 
 * Get ONLY Airports  
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es) 
 */
public class MainAirport {		
	final static String LANGUAGE = "es"; 	
	final static Hashtable<Integer, String> hashtable = new Hashtable<Integer, String>(30*1000);   
			
	/**
	 * 
	 * Main program to generate Location files for GATE
	 *  
	 * using Geonames (http://download.geonames.org/export/dump/) 
	 *      
	 * @param args
	 */
	public static void main(String[] args) throws LangDetectException {
		String langProfilesDirectoryName = "src/main/resources/profiles"; 
		String inputFileNamePath = "/home/gsantos/geonames/data/allCountries.txt";
		String inputFileNameAlternatePath = "/home/gsantos/geonames/data/alternateNames.txt";
		String outputFileNamePath = "src/main/resources/output/airports.lst";
				
		File langProfilesDirectory = new File(langProfilesDirectoryName);		
		// Load language profiles  
		DetectorFactory.loadProfile(langProfilesDirectory);
		if (!DetectorFactory.getLangList().isEmpty()) {
			System.out.println("Loaded languages list : " + DetectorFactory.getLangList().size());
		} 
											
		
		Preconditions.checkNotNull(inputFileNamePath, "Input filename allCountries.text (Geonames) should NOT be NULL");
		Preconditions.checkNotNull(inputFileNameAlternatePath, "Input filename alternateNames.txt (Geonames) should NOT be NULL");
		Preconditions.checkNotNull(outputFileNamePath, "Input filename should NOT be NULL.");
		
		File inputFile = new File(inputFileNamePath); 
		File inputFileAlternateName = new File(inputFileNameAlternatePath);
		File outputFile = new File(outputFileNamePath);
			
		Preconditions.checkArgument(inputFile.exists(), "File does not exist: %s", inputFile);		
		Preconditions.checkArgument(inputFileAlternateName.exists(), "File does not exist: %s", inputFileAlternateName);
		if (outputFile.exists()) outputFile.delete();
		
		
		
		System.out.println(String.format("Starting process for language %s ....", LANGUAGE));
		System.out.println("> Waiting....");
		Stopwatch stopWatch = new Stopwatch();
		stopWatch.start();
		String resultado = "";
		try {
			// if file exists then remove it								
			resultado = Files.readLines(inputFileAlternateName, Charsets.UTF_8,
					new GeonamesAlternateNamesLineProcessor(hashtable, LANGUAGE)); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println(String.format(
				"For language %s, processed registers %s ", LANGUAGE, resultado));	
		System.out.println(String.format(
				"Complete process. Elapsed time %d min, %d sec.",
				stopWatch.elapsedTime(TimeUnit.MINUTES),
				stopWatch.elapsedTime(TimeUnit.SECONDS)));
		System.out.println("");		
				
				
		System.out.println("Starting process ....");
		System.out.println("> Waiting....");
		Stopwatch stopWatch1 = new Stopwatch();
		stopWatch1.start();
		String resultado1 = ""; 
		try {
				resultado1 = Files.readLines(inputFile, Charsets.UTF_8, new GeonamesAirportAllCountriesLineProcessor(outputFile, hashtable, LANGUAGE));
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
