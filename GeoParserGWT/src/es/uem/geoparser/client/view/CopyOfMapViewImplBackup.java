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

package es.uem.geoparser.client.view;

/*
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.HasInfoWindow;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.HasLatLngBounds;
import com.google.gwt.maps.client.base.InfoWindow;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.event.Event;
import com.google.gwt.maps.client.event.EventCallback;
import com.google.gwt.maps.client.overlay.HasMarker;
import com.google.gwt.maps.client.overlay.Marker;
import es.uem.geoparser.client.Constant;
import es.uem.geoparser.client.presenter.GeoRSSPresenter.Display;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;*/

/**
 * 
 * 	Vista mapa 
 * 
 * @author Guillermos Santos (gsantosgo@yahoo.es)
 */
public class CopyOfMapViewImplBackup { // extends Composite implements Display {
/*
	final private static int ZOOM = 2;
	final private static LatLng CENTER = new LatLng(0.0, 0.0);	
	final private static String MAP_TYPE = new MapTypeId().getRoadmap();

	final private MapWidget mapWidget;

	private static MapViewUiBinder uiBinder = GWT.create(MapViewUiBinder.class);

	interface MapViewUiBinder extends UiBinder<Widget, CopyOfMapViewImplBackup> {	
	}

	@UiField
	Label fuenteRSSLabel;
	@UiField
	Anchor fuenteRSSLink;
	@UiField
	Label fuenteRSSDetailLabel;
	@UiField
	SimplePanel mapWrapper;
	@UiField 
	FlowPanel newsList; 

	public CopyOfMapViewImplBackup() {
		initWidget(uiBinder.createAndBindUi(this));
		final MapOptions options = new MapOptions();
		options.setZoom(ZOOM);
		options.setCenter(CENTER);
		options.setMapTypeId(MAP_TYPE);
		options.setDraggable(true);
		options.setNavigationControl(true);
		options.setMapTypeControl(true);
		mapWidget = new MapWidget(options);
		mapWrapper.setWidget(mapWidget);
		mapWidget.setSize(Constant.MAP_WIDTH, Constant.MAP_HEIGHT);		
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setFuenteRSS(String name) {
		fuenteRSSLabel.setText(name); 
	}

	@Override
	public void setFuenteRSSLink(String url) {
		fuenteRSSLink.setHref(url);
		fuenteRSSLink.setText(url); 
	}

	@Override
	public void setFuenteRSSDetail(String html) {
		fuenteRSSDetailLabel.setText(html);
	}

	@Override	
	public void setNewsList(List<HasMarker> markers) {		
		newsList.clear(); 					
		for (int i=0; i < markers.size();i++) {						
		    final Anchor a = new Anchor(markers.get(i).getTitle());
		    a.addClickHandler(new ClickHandler() {			
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					
				}
			}); 
		    newsList.add(a); 
		}		
	}	
	
	@Override
	public HasMap getMap() {
		return mapWidget.getMap();
	}

	@Override
	public void fitBounds(HasLatLngBounds bounds) {
		mapWidget.fitBounds(bounds);
		
	}

	@Override
	public HasLatLng createLatLng(double lat, double lng) {
		return new LatLng(lat, lng);
	}

	@Override
	public HasLatLngBounds createBounds(HasLatLng southWest, HasLatLng northEast) {
		return new LatLngBounds(southWest, northEast);
	}

	@Override
	public HasMarker createMarkerAt(HasLatLng position) {
		   final Marker marker = new Marker();
		   marker.setMap(getMap());
		   marker.setPosition(position);
		   return marker;
	}

	@Override
	public HasInfoWindow createInfoWindow(String content) {
		final InfoWindow infoWindow = new InfoWindow();
		infoWindow.setContent(content);
		return infoWindow;
	}

	@Override
	public void addListener(HasJso instance, String eventName,
			EventCallback callback) {
		Event.addListener(instance, eventName, callback);
		
	}

	@Override
	public void clearInstanceListeners(HasJso instance) {
		Event.clearInstanceListeners(instance);		
	}
*/ 
}
