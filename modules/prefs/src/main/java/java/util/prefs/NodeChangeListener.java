/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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


package java.util.prefs;

import java.util.EventListener;
import java.util.prefs.NodeChangeEvent;

/**
 * This interface is used to handle preferences node change event. Implmenetation 
 * of this interface can be installed by <code>Preferences</code> instance.
 * 
 * @see Preferences
 * @see NodeChangeEvent
 * 
 * @since 1.4
 */
public interface NodeChangeListener extends EventListener {
	/**
	 * This method gets called whenever a child is added to a node.
	 * 
	 * @param e Node change event.
	 */
	public void childAdded (NodeChangeEvent e);
	
	/**
	 * This method gets called whenever a child is removed from a node.
	 * 
	 * @param e Node change event.
	 */
	public void childRemoved (NodeChangeEvent e);
}


 
