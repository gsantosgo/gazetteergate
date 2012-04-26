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
package es.uem.geoparser.client.bean;

/**
 * 
 * Bean para tratar elementos de lista de noticias  
 * 
 * @author Guillermos Santos (gsantosgo@yahoo.es)
 */
public class NewItem {

	private String name;
	private String html;

	public NewItem(final String name, final String html) {
		super();
		setName(name);
		setHtml(html);
	}

	public String getName() {
		return name;
	}

	public String getHtml() {
		return html;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NewItem)) {
			return false;
		}
		return this.name.equals(((NewItem) obj).name);
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}
