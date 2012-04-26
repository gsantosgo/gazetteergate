package es.uem.geoparser.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * Noticia del mapa 
 * @author Guillermo Santos (gsantosgo@yahoo.es) 
 */
@SuppressWarnings("serial")
public class NewMap implements Serializable {	
	private String placename = "";
	private Double latitude = null;  
	private Double longitude = null;
	private List<Article> articles = new ArrayList<Article>(); 
	
	public NewMap() { 		
	}
	
	public NewMap(String placename, Double latitude, Double longitude,
			List<Article> articles) {
		this.placename = placename;
		this.latitude = latitude;
		this.longitude = longitude;
		this.articles = articles;
	}

	public String getPlacename() {
		return placename;
	}
	
	public void setPlacename(String placename) {
		this.placename = placename;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public List<Article> getArticles() {
		return this.articles; 
	}

	@Override
	public String toString() {		
		StringBuilder sb = new StringBuilder(); 
		sb.append("NewMap [placename="); 
		sb.append(placename); 
		sb.append(", latitude="); 
		sb.append(latitude); 
		sb.append(", longitude="); 
		sb.append(longitude); 
		sb.append(", articles="); 
		sb.append(articles); 
		sb.append("]"); 
		
		return sb.toString();		
	}
	

	
	
			
}
