package es.uem.gazetteer.geonames.lineprocessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

/**
 * Geonames LineProcessor (allCountries.txt)
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es)
 */
public class GeonamesAllCountriesLineProcessor implements LineProcessor<String> {
	final static String NEW_LINE = System.getProperty("line.separator");
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
		}
	};

	final static String[] fileNames = { filters.get("A"), filters.get("H"),
			filters.get("L"), filters.get("P"), filters.get("R"),
			filters.get("S"), filters.get("T"), filters.get("U"),
			filters.get("V") };

	public final String SEPARATOR = "\t";
	private int lineCount = 0;
	private String outputDirectoryNamePath = null;
	private Hashtable<Integer, String> alternateNamesLanguage = null;
	private String language = null;
	private File[] outputFiles = new File[fileNames.length];

	/**
	 * Constructor
	 * 
	 * @param filter
	 *            Search filter
	 * @param file
	 *            File
	 */
	public GeonamesAllCountriesLineProcessor(String outputDirectoryNamePath,
			Hashtable<Integer, String> alternateNamesLanguage, String language) {
		this.lineCount = 0;
		this.outputDirectoryNamePath = outputDirectoryNamePath;
		this.alternateNamesLanguage = alternateNamesLanguage;
		this.language = language;
		initFiles();
	}

	/**
	 * Files Initialization
	 */
	private void initFiles() {
		for (int i = 0; i < fileNames.length; i++) {
			String uri = outputDirectoryNamePath + File.separator
					+ fileNames[i] + EXTENSION;
			System.out.println("Fichero creado > " + uri);
			outputFiles[i] = new File(uri);
			// If file exists then remove it
			if (outputFiles[i].exists())
				outputFiles[i].delete();
		}
	}

	/*
	 * =============================================== Information file Geonames
	 * allCountries.txt Format UTF-8
	 * 
	 * Columns
	 * 
	 * geonameid : integer id of record in geonames database name : name of
	 * geographical point (utf8) varchar(200) asciiname : name of geographical
	 * point in plain ascii characters, varchar(200) alternatenames :
	 * alternatenames, comma separated varchar(5000) latitude : latitude in
	 * decimal degrees (wgs84) longitude : longitude in decimal degrees (wgs84)
	 * feature class : see http://www.geonames.org/export/codes.html, char(1)
	 * feature code : see http://www.geonames.org/export/codes.html, varchar(10)
	 * country code : ISO-3166 2-letter country code, 2 characters cc2 :
	 * alternate country codes, comma separated, ISO-3166 2-letter country code,
	 * 60 characters admin1 code : fipscode (subject to change to iso code), see
	 * exceptions below, see file admin1Codes.txt for display names of this
	 * code; varchar(20) admin2 code : code for the second administrative
	 * division, a county in the US, see file admin2Codes.txt; varchar(80)
	 * admin3 code : code for third level administrative division, varchar(20)
	 * admin4 code : code for fourth level administrative division, varchar(20)
	 * population : bigint (8 byte int) elevation : in meters, integer dem :
	 * digital elevation model, srtm3 or gtopo30, average elevation of 3''x3''
	 * (ca 90mx90m) or 30''x30'' (ca 900mx900m) area in meters, integer. srtm
	 * processed by cgiar/ciat. timezone : the timezone id (see file
	 * timeZone.txt) varchar(40) modification date : date of last modification
	 * in yyyy-MM-dd format
	 */

	/**
	 * Process line files
	 */
	public boolean processLine(String line) throws IOException {
		if (!Strings.isNullOrEmpty(line)) {
			String[] columns = line.split(SEPARATOR);

			//
			Integer geonameId = Integer.parseInt(columns[0]);
			String featureClass = columns[6];
			String name = columns[2];
			String alternateNames = columns[3];

			// Compile all names ====
			List<String> names = new ArrayList<String>();
			names.add(name);
			// List of alternateNames
			if (!Strings.isNullOrEmpty(alternateNames)) {
				Iterable<String> alternateNameIterable = Splitter.on(',')
						.trimResults().omitEmptyStrings().split(alternateNames);
				Iterator<String> iterator = alternateNameIterable.iterator();
				while (iterator.hasNext()) {
					names.add(iterator.next());
				}
			}

			// unique Names
			List<String> uniqueNames = new ArrayList<String>(
					new HashSet<String>(names));
			String principalName = "";
			List<String> principalAlternateNameList = new ArrayList<String>();
			if (uniqueNames.size() > 0) {
				if (alternateNamesLanguage.containsKey(geonameId)) {
					principalName = alternateNamesLanguage.get(geonameId);
					principalAlternateNameList.add(principalName);
				}

				Collection<String> uniqueNamesColllection = Collections2
						.filter(uniqueNames, new ShouldLanguagePredicate(
								this.language));

				for (String uniqueName : uniqueNamesColllection) {
					if (!uniqueName.equals(principalName)) {
						principalAlternateNameList.add(uniqueName);
					}
				}
			}

			lineCount++;
			if ((lineCount % 1000) == 0)
				System.out.println(". (" + line + ")");

			if (featureClass.equals("A")) {
				writelnStringList(principalAlternateNameList, outputFiles[0]);
			} else if (featureClass.equals("H")) {
				writelnStringList(principalAlternateNameList, outputFiles[1]);
			} else if (featureClass.equals("L")) {
				writelnStringList(principalAlternateNameList, outputFiles[2]);
			} else if (featureClass.equals("P")) {
				writelnStringList(principalAlternateNameList, outputFiles[3]);
			} else if (featureClass.equals("R")) {
				writelnStringList(principalAlternateNameList, outputFiles[4]);
			} else if (featureClass.equals("S")) {
				writelnStringList(principalAlternateNameList, outputFiles[5]);
			} else if (featureClass.equals("T")) {
				writelnStringList(principalAlternateNameList, outputFiles[6]);
			} else if (featureClass.equals("U")) {
				writelnStringList(principalAlternateNameList, outputFiles[7]);
			} else if (featureClass.equals("V")) {
				writelnStringList(principalAlternateNameList, outputFiles[8]);
			}
		}

		return true;
	}

	/**
	 * 
	 * Write list of strings on File
	 * 
	 * @param texts
	 *            List of strings
	 * @param file
	 *            File
	 * @throws IOException
	 */
	public void writelnStringList(List<String> texts, File file)
			throws IOException {
		for (String text : texts) {
			writelnString(text, file);
		}
	}

	/**
	 * Write string on File
	 * 
	 * @param text
	 *            String
	 * @param file
	 *            File
	 * @throws IOException
	 */
	public void writelnString(String text, File file) throws IOException {
		if (!Strings.isNullOrEmpty(text)) {
			Files.append(text + NEW_LINE, file, Charsets.UTF_8);
		}
	}

	/**
	 * Result
	 */
	public String getResult() {
		return new StringBuffer().append(lineCount).toString();
	}

}