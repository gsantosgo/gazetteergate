package es.uem.gazetteer.geonames.lineprocessor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Geonames LineProcessor (allCountries.txt)  
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es) 
 */
public class GeonamesAllCountriesLineProcessor implements LineProcessor<String> {
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
	
	final static String[] fileNames= {filters.get("A"), 
			filters.get("H"), filters.get("L"), filters.get("P"),
			filters.get("R"), filters.get("S"), filters.get("T"),
			filters.get("U"), filters.get("V")
	};

	public final String SEPARATOR = "\t";
	private int lineCount = 0;
	private String outputDirectoryNamePath = null;
	private String language = null;
	private File[] outputFiles = new File[fileNames.length]; 
	
		
	
	
	/** 
	 * Constructor 
	 * 
	 * @param filter Search filter  
	 * @param file File 
	 */
	public GeonamesAllCountriesLineProcessor(String outputDirectoryNamePath, String language) {
		this.outputDirectoryNamePath = outputDirectoryNamePath;				
		this.language = language;
		initFiles(); 
	}
	
	/**
	 *  Inicializando ficheros 
	 */
	private void initFiles() {					
		for (int i = 0; i < fileNames.length; i++) {
			String uri = outputDirectoryNamePath + File.separator + fileNames[i] + EXTENSION; 
			System.out.println("Fichero creado: " + uri);
			outputFiles[i] = new File(uri);
			if (outputFiles[i].exists()) outputFiles[i].delete(); 
		}				
	}
	
	/*
	 * =============================================== 
	 * Information file Geonames allCountries.txt 
	 * Format UTF-8 
	 * 
	 * Columns  
	 * 
	 * geonameid         : integer id of record in geonames database
	 * name              : name of geographical point (utf8) varchar(200)
	 * asciiname         : name of geographical point in plain ascii characters, varchar(200)
	 * alternatenames    : alternatenames, comma separated varchar(5000)
	 * latitude          : latitude in decimal degrees (wgs84)
	 * longitude         : longitude in decimal degrees (wgs84)
	 * feature class     : see http://www.geonames.org/export/codes.html, char(1)
	 * feature code      : see http://www.geonames.org/export/codes.html, varchar(10)
	 * country code      : ISO-3166 2-letter country code, 2 characters
	 * cc2               : alternate country codes, comma separated, ISO-3166 2-letter country code, 60 characters
	 * admin1 code       : fipscode (subject to change to iso code), see exceptions below, see file admin1Codes.txt for display names of this code; varchar(20)
	 * admin2 code       : code for the second administrative division, a county in the US, see file admin2Codes.txt; varchar(80) 
	 * admin3 code       : code for third level administrative division, varchar(20)
	 * admin4 code       : code for fourth level administrative division, varchar(20)
	 * population        : bigint (8 byte int) 
	 * elevation         : in meters, integer
	 * dem               : digital elevation model, srtm3 or gtopo30, average elevation of 3''x3'' (ca 90mx90m) or 30''x30'' (ca 900mx900m) area in meters, integer. srtm processed by cgiar/ciat.
	 * timezone          : the timezone id (see file timeZone.txt) varchar(40)
	 * modification date : date of last modification in yyyy-MM-dd format
	 *  
	 */

	public boolean processLine(String line) throws IOException {
		if (!Strings.isNullOrEmpty(line)) {
			String[] columns = line.split(SEPARATOR);
			
			String featureClass = columns[6]; 			
			String name = columns[2];
			String alternateNames = columns[3];														
			
			lineCount++;
					
			if (featureClass.equals("A")) {
				Files.append(name + "\n", outputFiles[0],Charsets.UTF_8);
			} else if (featureClass.equals("H")) {
				Files.append(name + "\n", outputFiles[1],Charsets.UTF_8);
			} else if (featureClass.equals("L")) {
				Files.append(name + "\n", outputFiles[2],Charsets.UTF_8);
			} else if (featureClass.equals("P")) {
				Files.append(name + "\n", outputFiles[3],Charsets.UTF_8);
			} else if (featureClass.equals("R")) {
				Files.append(name + "\n", outputFiles[4],Charsets.UTF_8);
			} else if (featureClass.equals("S")) {
				Files.append(name + "\n", outputFiles[5],Charsets.UTF_8);
			} else if (featureClass.equals("T")) {
				Files.append(name + "\n", outputFiles[6],Charsets.UTF_8);
			} else if (featureClass.equals("U")) {
				Files.append(name + "\n", outputFiles[7],Charsets.UTF_8);
			} else if (featureClass.equals("V")) {
				Files.append(name + "\n", outputFiles[8],Charsets.UTF_8);
			}				

			
			/* 
				List aa = Lists.newArrayList();					
				Collections2.filter(aa, new ShouldLanguagePredicate(this.language));
							
				
				//Filtrar aquella que son españolas 
				Collection<String> alternateNamesCol = filter 
				// Nombres 
				List<String> names = Lists.newArrayList();
				
				
				
				Sets.newHashSet()
				if (featureClass.equals(this.filter)
						&& !Strings.isNullOrEmpty(name)) {
					lineCount++;					
					
					
					Files.append(name + "\n", this.file,Charsets.UTF_8);
				} 
			}*/ 
		}
		return true;
	}

	public String getResult() {
		return new StringBuffer().append(lineCount).toString();
	}

}