package es.uem.gazetteer.geonames.lineprocessor;

import java.io.IOException;
import java.util.Hashtable;

import com.google.common.base.Strings;
import com.google.common.io.LineProcessor;

/**
 * Geonames LineProcessor (alternateNames.txt)
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es)
 */
public class GeonamesAlternateNamesLineProcessor implements
		LineProcessor<String> {
	public final String SEPARATOR = "\t";
	private int lineCount = 0;
	private Hashtable<Integer, String> alternateNames;
	private String language = null;
	
	/**
	 * Constructor 
	 * 
	 * @param alternateNames  
	 * @param language es,en,fr,... 
	 */
	public GeonamesAlternateNamesLineProcessor(
			Hashtable<Integer, String> alternateNames, String language) {
		this.alternateNames = alternateNames;
		this.language = language;
	}

	/*
	 * =============================================== 
	 * Information file Geonames alternateNames.txt 
	 * Format UTF-8
	 * 
	 * Columns
	 * 
	 * alternateNameId : the id of this alternate name, int geonameid :
	 * geonameId referring to id in table 'geoname', int isolanguage : iso 639
	 * language code 2- or 3-characters; 4-characters 'post' for postal codes
	 * and 'iata','icao' and faac for airport codes, fr_1793 for French
	 * Revolution names, abbr for abbreviation, link for a website, varchar(7)
	 * alternate name : alternate name or name variant, varchar(200)
	 * isPreferredName : '1', if this alternate name is an official/preferred
	 * name isShortName : '1', if this is a short name like 'California' for
	 * 'State of California' *
	 */

	public boolean processLine(String line) throws IOException {
		if (!Strings.isNullOrEmpty(this.language)
				&& !Strings.isNullOrEmpty(line)) {
			String[] columns = line.split(SEPARATOR);						
			String geonameID = columns[1];
			String isoLanguage = columns[2];
			String alternateName = columns[3];		
			
			if (!Strings.isNullOrEmpty(isoLanguage) && 
				 isoLanguage.equals(this.language)) {
				lineCount++;										
				// Numberformatexception 
				this.alternateNames.put(Integer.parseInt(geonameID), alternateName);
			}

		}
		return true;
	}

	public String getResult() {
		return new StringBuffer().append(lineCount).toString();
	}

}