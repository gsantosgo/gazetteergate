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

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import es.uem.geoparser.client.presenter.GeoRSSPresenter;
import es.uem.geoparser.client.presenter.Presenter;
import es.uem.geoparser.client.view.MapView;
import es.uem.geoparser.client.view.MapViewImpl;
import es.uem.geoparser.shared.NewMap;

/**
 * Controlador para escuchar eventos y tomar acciones relevantes.
 *  
 * @author Guillermo Santos (gsantosgo@yahoo.es)
 */
public class AppController implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final GeoRSSServiceAsync geoRSSService;
	private MapViewImpl<NewMap> mapView = null;	
	private HasWidgets container;
	

	/**
	 * Constructor Controlador 
	 * 
	 * @param geoRSSService Servicio GeoRSS
	 * @param eventBus
	 */
	public AppController(GeoRSSServiceAsync geoRSSService, HandlerManager eventBus) {
		super();
		this.geoRSSService = geoRSSService;
		this.eventBus = eventBus;
		bind(); 
	}
		
	private void bind() {
		    History.addValueChangeHandler(this);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
	    String token = event.getValue();
	    
	    if (token != null) {
	      if (token.equals("display")) {
	        GWT.runAsync(new RunAsyncCallback() {
	          public void onFailure(Throwable caught) {
	          }
	          
	          public void onSuccess() {
	            // lazily initialize our views, and keep them around to be reused
	            //
	        	if (mapView == null) {
	        		mapView = new MapViewImpl<NewMap>();
	        	}
	        	new GeoRSSPresenter(geoRSSService, eventBus, mapView).go(container); 
	          }
	        });
	      }		
	    }
	}

	@Override
	public void go(HasWidgets container) {
	    this.container = container;
	    
	    if ("".equals(History.getToken())) {
	      History.newItem("display");
	    }
	    else {
	      History.fireCurrentHistoryState();
	    }	
	}						
}
