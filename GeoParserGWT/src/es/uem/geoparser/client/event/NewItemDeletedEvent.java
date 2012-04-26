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
 * Navigation item deleted from db.
 * 
 * @author Guillermos Santos (gsantosgo@yahoo.es)
 */
public class NewItemDeletedEvent extends
		GwtEvent<NewItemDeletedHandler> {

	public static final Type<NewItemDeletedHandler> TYPE = new Type<NewItemDeletedHandler>();
	final private NewItem newItem;

	/**
	 * @param navItem
	 */
	public NewItemDeletedEvent(NewItem newItem) {
		super();
		this.newItem = newItem;
	}

	@Override
	protected void dispatch(NewItemDeletedHandler handler) {
		handler.onNewItemDeleted(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<NewItemDeletedHandler> getAssociatedType() {
		return TYPE;
	}

	public NewItem getNewItem() {
		return newItem;
	}

}
