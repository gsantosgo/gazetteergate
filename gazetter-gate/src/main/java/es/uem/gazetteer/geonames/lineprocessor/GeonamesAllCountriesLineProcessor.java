package es.uem.gazetteer.geonames.lineprocessor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

/**
 * Geonames LineProcessor (AllCountries)  
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es) 
 */
public class GeonamesAllCountriesLineProcessor implements LineProcessor<String> {
	public final String SEPARATOR = "\t";
	public final char MULTI_WORD_SPACE = '+';
	private int lineCount = 0;
	private String filter = null;
	private File file = null;

	/** 
	 * Constructor 
	 * 
	 * @param filter Search filter  
	 * @param file File 
	 */
	public GeonamesAllCountriesLineProcessor(String filter, File file) {
		this.filter = filter;
		this.file = file;
	}
	
	/*
	 * =============================================== 
	 * Información estructura del fichero AllCountries 
	 * Formato UTF-8 
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
		if (!Strings.isNullOrEmpty(this.filter) && 
			!Strings.isNullOrEmpty(line)) {
			String[] columns = line.split(SEPARATOR);
			
			if (columns.length == 19) {
				String featureClass = columns[6]; 			
				String name = columns[2];
				String alternateNames = columns[3];														
						
				
				List aa = Lists.newArrayList();
				
				Collections2.filter(aa, new ShouldLanguagePredicate()); 
				
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
			}
		}
		return true;
	}

	public String getResult() {
		return new StringBuffer().append(lineCount).toString();
	}

}