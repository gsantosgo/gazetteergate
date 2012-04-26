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

import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.base.HasInfoWindow;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.HasLatLngBounds;
import com.google.gwt.maps.client.event.EventCallback;
import com.google.gwt.maps.client.overlay.HasMarker;
import com.google.gwt.user.client.ui.Widget;

/**  
 * 
 * @author Guillermos Santos (gsantosgo@yahoo.es)
 */
public interface MapView<T> {
	
	public interface Presenter<T> {				
	}
	
	HasMap getMap();
	void fitBounds(HasLatLngBounds bounds);
	HasLatLng createLatLng(double lat, double lng);
	HasLatLngBounds createBounds(HasLatLng southWest, HasLatLng northEast);
	HasMarker createMarkerAt(HasLatLng position);
	HasInfoWindow createInfoWindow(String content);

	void addListener(HasJso instance, String eventName,
			EventCallback callback);
	void clearInstanceListeners(HasJso instance);

	
	void setPresenter(Presenter<T> presenter);
	Widget asWidget();	
}
