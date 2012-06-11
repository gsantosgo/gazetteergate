package es.uem.gazetteer.geonames.lineprocessor;

import java.io.IOException;
import java.util.Hashtable;

import com.google.common.base.Strings;
import com.google.common.io.LineProcessor;

public class StopWordLineProcessor implements LineProcessor<String> {
	public final String SEPARATOR = "\t";
	private int lineCount = 0;
	private Hashtable<String, String> stopWords;

	// private File file = null;

	/**
	 * 
	 * Constructor
	 * 
	 */
	public StopWordLineProcessor(Hashtable<String, String> stopWords) {
		this.stopWords = stopWords; 
	}

	/**
	 * 
	 * Procesamiento de lineas
	 * 
	 */
	public boolean processLine(String line) throws IOException {
		if (!Strings.isNullOrEmpty(line)) {
			this.stopWords.put(line,line);
			lineCount++;
		}
		return true;
	}

	public String getResult() {
		return new StringBuffer().append(lineCount).toString();
	}


}
