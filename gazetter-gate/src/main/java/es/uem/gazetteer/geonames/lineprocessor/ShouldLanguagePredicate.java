package es.uem.gazetteer.geonames.lineprocessor;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.google.common.base.Predicate;

public class ShouldLanguagePredicate implements Predicate<String>{ 
	private String language;
	
	public ShouldLanguagePredicate(String language) {
		this.language = language;
	}

	public boolean apply(String name) {		 
		Detector detector;
		try {
			detector = DetectorFactory.create();
			detector.append(name);
			return detector.detect().equals(this.language);
			
		} catch (LangDetectException e) {
			e.printStackTrace();
			return false; 
		}				
	}	
}
