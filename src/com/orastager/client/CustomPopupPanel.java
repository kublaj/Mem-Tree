/*
 * Copyright 2011 Nanda Emani
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

package com.orastager.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class CustomPopupPanel extends PopupPanel{
	Boolean setCurrNodeFocus = false;
	Image imgClose = new Image(StaticVar.menuResources.closeTab());
	Boolean closeButton;
	
	CustomPopupPanel(Boolean autoHide, Boolean glassEnabled, Boolean currNodeFocus, Boolean closeButtonEnabled){
		super(autoHide);
		setCurrNodeFocus = currNodeFocus;
		this.closeButton = closeButtonEnabled;
		setGlassEnabled(glassEnabled);
		setAnimationEnabled(true);
		setAnimationType(PopupPanel.AnimationType.ONE_WAY_CORNER);
		imgClose.setTitle("close");
		//imgClose.setStyleName("gwt-dmaImage");
		imgClose.addStyleName("closePopupButton");
	}
	
	/*CustomPopupPanel(boolean autoHide){
		super(autoHide);
		setGlassEnabled(true);
		setAnimationEnabled(true);
	}*/
	
	public void show(){
		super.show();
		if (getElement() != null && closeButton)
			getElement().appendChild(imgClose.getElement());
	}
	
	@Override
    protected void onPreviewNativeEvent(NativePreviewEvent event) {
        super.onPreviewNativeEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide();
                    if (setCurrNodeFocus)
                    	StaticVar.currNode.setFocus();
                }
                break;
            case Event.ONCLICK:
            	if (Element.as(event.getNativeEvent().getEventTarget()).equals(imgClose.getElement()))
            		hide();
            	break;
        }
    }
}
