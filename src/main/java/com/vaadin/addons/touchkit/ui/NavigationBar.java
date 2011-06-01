package com.vaadin.addons.touchkit.ui;

import java.util.Iterator;
import java.util.LinkedList;

import com.vaadin.addons.touchkit.gwt.client.VNavigationBar;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.Component;

/**
 * A native looking navigation bar that contains an xhtml caption in the middle
 * and component areas (most commonly for buttons) on the left and right.
 */
@ClientWidget(VNavigationBar.class)
public class NavigationBar extends AbstractComponentContainer {

    private static final String STYLENAME = "v-navitiotionbar";
    private static final String BACK_BUTTON = STYLENAME + "-backbutton";
    private NavigationButton backButton = new NavigationButton();
    private Component navigationBarComponent;
    private Component leftNavigationBarComponent;

    public NavigationBar() {
        setStyleName(STYLENAME);
        backButton.setVisible(false);
        backButton.setStyleName(BACK_BUTTON);
        setLeftNavigationBarComponent(backButton);
    }

    /**
     * Sets the component on the left side of the caption.
     * 
     * <p>
     * This place is most commonly reserved for the back button. In case the
     * {@link #setPreviousView(Component)} method is used, it replaces existing
     * components from this location.
     * 
     * @param c
     */
    public void setLeftNavigationBarComponent(Component c) {
        if (leftNavigationBarComponent != null) {
            super.removeComponent(leftNavigationBarComponent);
        }
        if (c != null) {
            super.addComponent(c);
        }
        leftNavigationBarComponent = c;
        requestRepaint();
    }

    /**
     * Sets the component on the right side of the caption.
     * 
     * @param c
     */
    public void setNavigationBarComponent(Component c) {
        if (navigationBarComponent != null) {
            super.removeComponent(navigationBarComponent);
        }
        if (c != null) {
            super.addComponent(c);
        }
        navigationBarComponent = c;
        requestRepaint();
    }

    public void setPreviousView(Component component) {
        getBackButton().setTargetView(component);
        if (getBackButton().getParent() == null) {
            setLeftNavigationBarComponent(getBackButton());
        }
        getBackButton().setVisible(component != null);
    }

    public Component getPreviousView() {
        return getBackButton().getTargetView();
    }

    private NavigationButton getBackButton() {
        return backButton;
    }

    /**
     * Not supported by NavigationBar.
     * 
     * @deprecated
     */
    @Deprecated
    @Override
    public void addComponent(Component c) {
        throw new UnsupportedOperationException(
                "Navigation bar does not support general container mutation methods. Use setRightComponent, setCaption and setPreviousVew methods to control the componen.");
    }

    /**
     * Not supported by NavigationBar.
     * 
     * @deprecated
     */
    @Deprecated
    public void replaceComponent(Component oldComponent, Component newComponent) {
        throw new UnsupportedOperationException(
                "Navigation bar does not support general container mutation methods. Use setRightComponent, setCaption and setPreviousVew methods to control the componen.");
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        if (leftNavigationBarComponent != null) {
            target.startTag("back");
            leftNavigationBarComponent.paint(target);
            target.endTag("back");
        }

        if (navigationBarComponent != null) {
            target.startTag("component");
            navigationBarComponent.paint(target);
            target.endTag("component");
        }

    }

    public Iterator<Component> getComponentIterator() {
        LinkedList<Component> components = new LinkedList<Component>();
        components.add(backButton);
        if (navigationBarComponent != null) {
            components.add(navigationBarComponent);
        }
        return components.iterator();
    }

}
