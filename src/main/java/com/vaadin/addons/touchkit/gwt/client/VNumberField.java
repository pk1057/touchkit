package com.vaadin.addons.touchkit.gwt.client;

import com.vaadin.terminal.gwt.client.ui.VTextField;

public class VNumberField extends VTextField {
	public VNumberField() {
		getElement().setPropertyString("type", "number");
	}
	
}
