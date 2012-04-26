package es.uem.gazetteer.jrc.lineprocessor;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

/**
 * JRCNames LineProcessor 
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es) 
 */
public class JRCNamesLineProcessor implements LineProcessor<String> {
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
	public JRCNamesLineProcessor(String filter, File file) {
		this.filter = filter;
		this.file = file;
	}
	
	/*
	 * ===============================================  
	 * File Information JRCNames entities   
	 * Format UTF-8 
	 * Columns id;type;language;name Each line
	 * 
	 * consists of four tab-separated columns containing: name ID; type;
	 * language; and name variant (see Table 2). Name ID is a unique numerical
	 * identifier for the entity. In this release, Type can only be Person (P)
	 * or Organisation (O). The column Language contains the ISO 639-2 two-digit
	 * code for the language if the name variant should only be looked up in
	 * that language. If a name can be looked up in all languages, which is the
	 * default, the value is u (undefined). The strings in the column name
	 * variant are the known spellings of the name, one per line. Multi-word
	 * strings are separated by the + sign (e.g. United+Nations).
	 */

	public boolean processLine(String line) throws IOException {
		if (!Strings.isNullOrEmpty(line)) {
			String[] columns = line.split(SEPARATOR);
			if (columns.length == 4) {
				String organization = columns[1];
				String name = columns[3];
				if (organization.equals(this.filter)
						&& !Strings.isNullOrEmpty(name)) {
					lineCount++;
					Files.append(name.replace(MULTI_WORD_SPACE, ' ') + "\n", this.file,
							Charsets.UTF_8);
				}
			}
		}
		return true;
	}

	public String getResult() {
		return new StringBuffer().append(lineCount).toString();
	}

}