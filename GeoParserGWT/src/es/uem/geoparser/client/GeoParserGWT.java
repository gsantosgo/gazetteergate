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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * 
 * Clase que define el punto de entrada (entry point)<code>onModuleLoad()</code>
 * 
 * @author Guillermo Santos (gsantosgo@yahoo.es)
 */
public class GeoParserGWT implements EntryPoint {

	final private HandlerManager eventBus = new HandlerManager(null);

	/**
	 * onModuleLoad
	 */
	@Override
	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				e.printStackTrace();
			}
		});
		GeoRSSServiceAsync geoRSSService = GWT.create(GeoRSSService.class);		
	    HandlerManager eventBus = new HandlerManager(null);
	    AppController appViewer = new AppController(geoRSSService, eventBus);
	    appViewer.go(RootPanel.get("application"));			
	}
}