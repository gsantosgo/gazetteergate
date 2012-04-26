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
package es.uem.geoparser.client;

import java.util.HashMap;
import java.util.Map;


/**
 * Cadena de caracteres estaticas y literales.
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es)
 */
public class Constant {

	private Constant() {
		throw new UnsupportedOperationException();
	}
	
	final public static String MAP_WIDTH = "100%";   
	final public static String MAP_HEIGHT = "100%";
		
	public static final Map<String, String> MENU_LIST  = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;
		
		{
			// ABC.ES
			put("abcPortadaMenuItem", "http://www.abc.es/rss/ticker.aspx?sc=abcPortada");
			put("abcUltimaHoraMenuItem", "http://www.abc.es/rss/ticker.aspx?sc=abc_ultima");

			// ELMUNDO.ES
			put("elmundoPortadaMenuItem", "http://elmundo.feedsportal.com/elmundo/rss/portada.xml");			
			put("elmundoEspanaMenuItem", "http://elmundo.feedsportal.com/elmundo/rss/espana.xml");
			put("elmundoInternacionalMenuItem","http://elmundo.feedsportal.com/elmundo/rss/internacional.xml");
			
			// ELPAIS.COM 
			put("elpaisPortadaMenuItem","http://ep00.epimg.net/rss/elpais/portada.xml");
			put("elpaisUltimaNoticiasMenuItem","http://ep00.epimg.net/rss/tags/ultimas_noticias.xml");
			put("elpaisInternacionalMenuItem","http://ep00.epimg.net/rss/internacional/portada.xml");
			
		}
	}; 
	   	
}
