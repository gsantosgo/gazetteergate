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

import java.util.List;

import com.google.gwt.maps.client.overlay.HasMarker;

/**
 * 
 * Interfaz para todas las páginas interfaz de usuario (page ui)
 * 
 * @author Guillermos Santos (gsantosgo@yahoo.es)
 */
public interface GeoRSSView extends View {
	void setFuenteRSS(String title);
	void setFuenteRSSLink(String url);
	void setFuenteRSSDetail(String detail);
	void setNewsList(List<HasMarker> markers);
}
