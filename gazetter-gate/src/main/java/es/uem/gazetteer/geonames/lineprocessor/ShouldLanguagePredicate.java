package es.uem.gazetteer.geonames.lineprocessor;

import com.google.common.base.Predicate;

public class ShouldLanguagePredicate implements Predicate<String>{
 
	public boolean apply(String name) {
		return false;
	}	
	/*
	public class ShouldNotHaveDigitsInLoginPredicate implements Predicate<User> {
	    @Override
	    public boolean apply(User user) {
	        checkNotNull(user);
	        return CharMatcher.DIGIT.retainFrom(user.login).length() == 0;
	    }   
	} */       
}
