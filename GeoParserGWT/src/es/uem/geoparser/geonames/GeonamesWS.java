/* Copyright (c) 2012 Guillermo Santos 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.uem.geoparser.geonames;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;


/**
 * Acceso a Servicio Web GeoNames para obtener
 * las coordenadas geográficas (latitud,longitud) de un toponimo.    
 * @author Guillermo Santos (gsantosgo@yahoo.es)
 */
public class GeonamesWS {	
	//private static final Logger logger = Logger.getLogger(GeonamesWS.class);	
	public static Toponym searchToponym(String toponymName) {
		
		if (toponymName == null || toponymName.length() == 0) {
			return null; 
		}
			
		Toponym toponymResult = null; 
		try { 
			ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
			searchCriteria.setMaxRows(1); 
			searchCriteria.setLanguage("ES"); 
			searchCriteria.setQ(toponymName);
			
			// Falta logger  
			// debug  
			WebService.setUserName("gsantosgo"); 
			ToponymSearchResult result = WebService.search(searchCriteria);
            if (result != null && result.getTotalResultsCount() > 0) {
                toponymResult = result.getToponyms().get(0);                 
            }										
		}
		catch (Exception e) {				
			System.out.println("Se ha producido excepción al buscar en Geonames el toponimo " + toponymName);
			// Falta detallar logger 
		}		
		return toponymResult;
	}

}
