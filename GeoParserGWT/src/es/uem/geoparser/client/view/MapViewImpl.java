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

import com.google.gwt.core.client.GWT;
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

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * 	Vista mapa.   
 * 
 * @author Guillermos Santos (gsantosgo@yahoo.es)
 */

public class MapViewImpl<T> extends Composite implements MapView<T> {

	@UiTemplate("MapView.ui.xml")	
	interface MapViewUiBinder extends UiBinder<Widget, MapViewImpl> {}	
	private static MapViewUiBinder uiBinder = GWT.create(MapViewUiBinder.class);
		
	private Presenter<T> presenter;
	
	final private static int ZOOM = 2;
	final private static LatLng CENTER = new LatLng(0.0, 0.0);	
	final private static String MAP_TYPE = new MapTypeId().getRoadmap();

	final private MapWidget mapWidget;
		
	
	@UiField
	MenuBar menuBar;
	
	// ABC.ES
	@UiField
	MenuItem abcPortadaMenuItem;	
	@UiField
	MenuItem abcUltimaHoraMenuItem;

	// ELMUNDO.ES
	@UiField
	MenuItem elmundoPortadaMenuItem; 			
	@UiField
	MenuItem elmundoEspanaMenuItem;
	@UiField
	MenuItem elmundoInternacionalMenuItem; 
	
	// ELPAIS.COM
	@UiField
	MenuItem elpaisPortadaMenuItem; 
	@UiField
	MenuItem elpaisUltimaNoticiasMenuItem;
	@UiField
	MenuItem elpaisInternacionalMenuItem; 
	
	
	@UiField
	SimplePanel contentNews;	
	
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
	
	
	public MapViewImpl() {
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
							
		// 
		abcPortadaMenuItem.setCommand(new Command() {			
			@Override
			public void execute() {			
				Window.alert(Constant.MENU_LIST.get("abcPortadaMenuItem"));							
			}
		});
		
				
		abcUltimaHoraMenuItem.setCommand(new Command() {			
			@Override
			public void execute() {			
				Window.alert(Constant.MENU_LIST.get("abcUltimaHoraMenuItem"));							
			}
		});

		// ELMUNDO.ES

		elmundoPortadaMenuItem.setCommand(new Command() {			
			@Override
			public void execute() {			
				Window.alert(Constant.MENU_LIST.get("elmundoPortadaMenuItem"));							
			}
		});
		
		elmundoEspanaMenuItem.setCommand(new Command() {			
			@Override
			public void execute() {			
				Window.alert(Constant.MENU_LIST.get("elmundoEspanaMenuItem"));							
			}
		});
		
		elmundoInternacionalMenuItem.setCommand(new Command() {			
			@Override
			public void execute() {			
				Window.alert(Constant.MENU_LIST.get("elmundoInternacionalMenuItem"));							
			}
		});
		
		// Enlace PAIS 

		elpaisPortadaMenuItem.setCommand(new Command() {			
			@Override
			public void execute() {			
				Window.alert(Constant.MENU_LIST.get("elpaisPortadaMenuItem"));							
			}
		});
		
		elpaisUltimaNoticiasMenuItem.setCommand(new Command() {			
			@Override
			public void execute() {			
				Window.alert(Constant.MENU_LIST.get("elpaisUltimaNoticiasMenuItem"));							
			}
		});						
		
		elpaisInternacionalMenuItem.setCommand(new Command() {			
			@Override
			public void execute() {			
				Window.alert(Constant.MENU_LIST.get("elpaisInternacionalMenuItem"));							
			}
		});			
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
		
	@Override
	public void setPresenter(
			es.uem.geoparser.client.view.MapView.Presenter<T> presenter) {
		this.presenter = presenter; 		
	}

	public Widget asWidget() {
		return this;
	}	
}
