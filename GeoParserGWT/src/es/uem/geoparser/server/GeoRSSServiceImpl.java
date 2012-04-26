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

package es.uem.geoparser.server;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import es.uem.geoparser.client.GeoRSSService;
import es.uem.geoparser.shared.Article;
import es.uem.geoparser.shared.NewMap;

/**
 * Implementación Servicio GeoRSS
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es)
 */

@SuppressWarnings("serial")
public class GeoRSSServiceImpl extends RemoteServiceServlet implements
		GeoRSSService {
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see es.uem.geoparser.client.GeoRSSServices#loadGeoRSSNews()
	 */
	@Override
	public List<NewMap> loadGeoRSSNews() {
		List<Article> articulos = new ArrayList<Article>();
		articulos.add(new Article("hola", "hola", "hola"));

		List<Article> articulos2 = new ArrayList<Article>();
		articulos2.add(new Article("hola2", "hola2", "hola2"));

		List<NewMap> lista = new ArrayList<NewMap>();
		lista.add(new NewMap("Madrid", 39.300, -3.23232, articulos));
		lista.add(new NewMap("Madrid", 40.32, -3.23232, articulos));
		lista.add(new NewMap("Madrid", 41.321, -3.1231, articulos2));
		lista.add(new NewMap("Madrid", 42.3123, -3.23423, articulos2));

		return lista;
	}

	/*
	@Override
	public ArrayList<NewMap> loadGeoRSSNews(String rssUrl) {	
		URL rssURL = new URL(rssUrl);
			      
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(rssURL));
		
		ArrayList<Article> articles 
		for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
	         System.out.println("Title: " + entry.getTitle());
             System.out.println("Unique Identifier: " + entry.getUri());
             System.out.println("Updated Date: " + entry.getUpdatedDate());

             // Get the Links
             for (SyndLinkImpl link : (List<SyndLinkImpl>) entry.getLinks()) {
                 System.out.println("Link: " + link.getHref());
             }            

             // Get the Contents
             for (SyndContentImpl content : (List<SyndContentImpl>) entry.getContents()) {
                 System.out.println("Content: " + content.getValue());
             }

             // Get the Categories
             for (SyndCategoryImpl category : (List<SyndCategoryImpl>) entry.getCategories()) {
                 System.out.println("Category: " + category.getName());
             }			
		}
		
		
		
         System.out.println("Feed Title: " + feed.getTitle()); 		
		
		List<Article> articulos = new ArrayList<Article>();
		articulos.add(new Article("hola", "hola", "hola"));

		List<Article> articulos2 = new ArrayList<Article>();
		articulos2.add(new Article("hola2", "hola2", "hola2"));

		List<NewMap> lista = new ArrayList<NewMap>();
		lista.add(new NewMap("Madrid", 39.300, -3.23232, articulos));
		lista.add(new NewMap("Madrid", 40.32, -3.23232, articulos));
		lista.add(new NewMap("Madrid", 41.321, -3.1231, articulos2));
		lista.add(new NewMap("Madrid", 42.3123, -3.23423, articulos2));

		return lista;
	}	*/
}
