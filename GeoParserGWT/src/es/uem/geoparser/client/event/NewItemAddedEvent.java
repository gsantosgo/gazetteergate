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
package es.uem.geoparser.client.event;

import com.google.gwt.event.shared.GwtEvent;
import es.uem.geoparser.client.bean.NewItem;

/**
 * Evento Noticias añadida 
 * New item added to the db.
 * 
 * @author Guillermos Santos (gsantosgo@yahoo.es)
 */
public class NewItemAddedEvent extends GwtEvent<NewItemAddedHandler> {

	public static final Type<NewItemAddedHandler> TYPE = new Type<NewItemAddedHandler>();

	final private NewItem newItem;

	/**
	 * @param navItem
	 */
	public NewItemAddedEvent(NewItem newItem) {
		super();
		this.newItem = newItem;
	}

	@Override
	protected void dispatch(NewItemAddedHandler handler) {
		handler.onNewItemAdded(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<NewItemAddedHandler> getAssociatedType() {
		return TYPE;
	}

	public NewItem getNewItem() {
		return newItem;
	}

}
