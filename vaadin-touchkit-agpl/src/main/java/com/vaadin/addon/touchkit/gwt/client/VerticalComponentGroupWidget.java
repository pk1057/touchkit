package com.vaadin.addon.touchkit.gwt.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.navigation.VNavigationButton;
import com.vaadin.terminal.gwt.client.StyleConstants;
import com.vaadin.terminal.gwt.client.ui.button.VButton;

public class VerticalComponentGroupWidget extends FlowPanel {

    public static final String TAGNAME = "verticalcomponentgroup";
    private static final String CLASSNAME = "v-touchkit-" + TAGNAME;
    public static final String CAPTION_CLASSNAME = "v-caption";

    private boolean firstElement = true;
    private HTML captionWidget;
    private FlowPanel content = new FlowPanel();

    public VerticalComponentGroupWidget() {
        content.addStyleName(CLASSNAME);
        captionWidget = new HTML("");
        captionWidget.addStyleName("v-touchkit-verticalcomponentgroup-caption");

        UIObject.setStyleName(getElement(), TAGNAME + "-"
                + StyleConstants.MARGIN_TOP, true);
        UIObject.setStyleName(getElement(), TAGNAME + "-"
                + StyleConstants.MARGIN_RIGHT, true);
        UIObject.setStyleName(getElement(), TAGNAME + "-"
                + StyleConstants.MARGIN_BOTTOM, true);
        UIObject.setStyleName(getElement(), TAGNAME + "-"
                + StyleConstants.MARGIN_LEFT, true);
    }

    public void setCaption(String caption) {
        captionWidget.setHTML(caption);
    }

    /**
     * Adds Widget with icon url and caption text
     * 
     * @param widget
     * @param icon
     * @param caption
     */
    public void add(final Widget widget, final String iconUrl,
            final String captionText) {
        if (firstElement) {
            firstElement = false;
            add(captionWidget);
            add(content);
        }
        if (iconUrl != null
                && !iconUrl.isEmpty()
                && !(widget instanceof VButton || widget instanceof VNavigationButton)) {
            IconWidget newIcon = new IconWidget(iconUrl);
            getElement().insertFirst(newIcon.getElement());
        }
        if (captionText != null
                && !captionText.isEmpty()
                && !(widget instanceof VButton || widget instanceof VNavigationButton)) {
            HTML caption = new HTML(captionText);
            caption.setStyleName(CAPTION_CLASSNAME);
            widget.addStyleName("v-touchkit-has-caption");
            content.add(caption);
        }

        content.add(widget);
    }
}
