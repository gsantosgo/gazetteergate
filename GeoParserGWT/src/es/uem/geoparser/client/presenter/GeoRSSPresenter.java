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
package es.uem.geoparser.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.maps.client.base.HasInfoWindow;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.event.EventCallback;
import com.google.gwt.maps.client.overlay.HasMarker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import es.uem.geoparser.client.GeoRSSServiceAsync;
import es.uem.geoparser.client.view.MapView;
import es.uem.geoparser.shared.Article;
import es.uem.geoparser.shared.NewMap;

/**
 * Add click event to a marker.
 * 
 * Zooms in to level '4' on click on the marker.
 * 
 * @author Guillermos Santos (gsantosgo@yahoo.es)
 */
public class GeoRSSPresenter implements Presenter , MapView.Presenter<NewMap> {
	
	private final GeoRSSServiceAsync geoRSSService;
	private final HandlerManager eventBus;
	private final MapView<NewMap> view;
	private final ArrayList<HasMarker> markers;	
	
	public GeoRSSPresenter(GeoRSSServiceAsync geoRSSService, 
			HandlerManager eventBus, MapView<NewMap> view) {
		super();
		this.geoRSSService = geoRSSService;
		this.eventBus = eventBus;
		this.view = view; 
		this.markers = new ArrayList<HasMarker>();		
	}

	private void fetchRSSNews() {						
		geoRSSService.loadGeoRSSNews(new AsyncCallback<List<NewMap>>() {
			@Override
			public void onSuccess(List<NewMap> result) {
				int resultados = result.size();
				if (result != null && resultados > 0) {
					for (int i = 0; i < resultados; ++i) {
						final NewMap newMap = result.get(i);
						final HasLatLng location = view.createLatLng(
								newMap.getLatitude(), newMap.getLongitude());
						final HasMarker marker = view.createMarkerAt(location);
						markers.add(marker);
						marker.setTitle(newMap.getPlacename());
						attachArticles(marker, newMap.getArticles());
					}		
/*										
					display.setFuenteRSSDetail("Marcadores de Noticias: " + markers.size());
					
					display.setNewsList(markers);*/  
				}
				
				/*
				 * for (int i = 0; i < 5; ++i) { final HasLatLng location =
				 * display.createLatLng(southWest.getLatitude() + (latSpan *
				 * Math.random()), southWest.getLongitude() + (lngSpan *
				 * Math.random())); final HasMarker marker =
				 * display.createMarkerAt(location); markers.add(marker); int j
				 * = i + 1; marker.setTitle("" + j); attachSecretMessage(marker,
				 * i); }
				 */

			}

			@Override
			public void onFailure(Throwable caught) { 
				Window.alert("Error obteniendo detalles de noticias");
			}
		});
	}

	/**
	 * Anexando articulos
	 * 
	 * @param marker
	 *            Marcador
	 * @param articles
	 *            Lista de articulos
	 */
	private void attachArticles(final HasMarker marker,
			final List<Article> articles) {
		final StringBuffer content = new StringBuffer();
		int numArticles = articles.size();
		for (int i = 0; i < numArticles; i++) {
			Article article = articles.get(i);
			content.append(article.getHeadline() + "."
					+ article.getDescription());
		}
		final HasInfoWindow infoWindow = view.createInfoWindow(content
				.toString());
			
		view.addListener(marker, "click", new EventCallback() {
			@Override
			public void callback() {
				infoWindow.open(view.getMap(), marker);
			}
		});
	}

	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchRSSNews(); 
	} 

}
