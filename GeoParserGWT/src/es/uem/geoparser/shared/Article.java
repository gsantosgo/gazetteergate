package es.uem.geoparser.shared;

import java.io.Serializable;

/** 
 * Articulo de la noticia
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es) 
 */
@SuppressWarnings("serial")
public class Article implements Serializable {
	private String articleID = "";  
	private String headline = ""; 
	private String description = ""; 
	private String href = ""; 
	
	public Article() {		
	}

	public Article(String headline, String description, String href) {
		this.headline = headline; 
		this.description = description; 
		this.href = href; 	
	}
	
	public String getArticleID() {
		return articleID;
	}

	public void setArticleID(String articleID) {
		this.articleID = articleID;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("Article [articleID=");
		sb.append(articleID);
		sb.append(", headline=");
		sb.append(headline); 
		sb.append(", description="); 
		sb.append(description);
		sb.append(", href="); 
		sb.append(href); 
		sb.append("]");	
		return sb.toString(); 
	}	
		
	

}
