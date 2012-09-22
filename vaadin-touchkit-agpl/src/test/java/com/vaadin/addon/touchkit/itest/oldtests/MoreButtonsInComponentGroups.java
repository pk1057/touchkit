package com.vaadin.addon.touchkit.itest.oldtests;

import java.util.Iterator;

import com.vaadin.addon.touchkit.AbstractTouchKitIntegrationTest;
import com.vaadin.addon.touchkit.ui.HorizontalButtonGroup;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class MoreButtonsInComponentGroups extends AbstractTouchKitIntegrationTest {

    public MoreButtonsInComponentGroups() {
        NavigationView navigationView = new NavigationView();
        navigationView.setCaption("Buttons in various places");

        CssLayout l = new CssLayout();

        Button button = new Button("Button not in a component group");
        button.setWidth("100%");
        l.addComponent(button);

        VerticalComponentGroup verticalComponentGroup = new VerticalComponentGroup(
                "Vertical component group");
        verticalComponentGroup.setWidth("100%");

        button = new Button("Button");
        button.setWidth("100%");
        verticalComponentGroup.addComponent(button);

        verticalComponentGroup.addComponent(new TextField("TextField"));

        button = new Button("Button too");
        button.setWidth("100%");
        verticalComponentGroup.addComponent(button);

        verticalComponentGroup.addComponent(new Label(
                "FIXME: Label, between buttons bugs"));

        button = new Button("Button too");
        button.setWidth("100%");
        verticalComponentGroup.addComponent(button);

        l.addComponent(verticalComponentGroup);

        verticalComponentGroup = new VerticalComponentGroup();
        verticalComponentGroup.setWidth("100%");
        verticalComponentGroup.setCaption("Horizontal in vertical");
        HorizontalButtonGroup horizontalGroup = getHorizontalGroup();
        horizontalGroup.setCaption("Caption");
        verticalComponentGroup.addComponent(horizontalGroup);

        horizontalGroup = getHorizontalGroup();
        horizontalGroup.addComponent(new Button("Third"));
        horizontalGroup.setWidth("300px");
        Iterator<Component> componentIterator = horizontalGroup
                .getComponentIterator();
        while (componentIterator.hasNext()) {
            Component next = componentIterator.next();
            next.setWidth("" + 100.0 / (horizontalGroup.getComponentCount())
                    + "%");
        }
        verticalComponentGroup.addComponent(horizontalGroup);

        horizontalGroup = getHorizontalGroup();
        Iterator<Component> it = horizontalGroup.getComponentIterator();
        it.next().setCaption("Only one here");
        horizontalGroup.removeComponent(it.next());
        verticalComponentGroup.addComponent(horizontalGroup);

        l.addComponent(verticalComponentGroup);

        l.addComponent(getHorizontalGroup());

        navigationView.setContent(l);

        Resource mailImage = new ClassResource(getClass(),"mail.png");
        
        HorizontalButtonGroup horizontalComponentGroup = new HorizontalButtonGroup();
        Button c = new Button();
        c.setIcon(mailImage);
        horizontalComponentGroup.addComponent(c);
        
        c = new Button("Up");
        c.setIcon(mailImage);
        horizontalComponentGroup.addComponent(c);
        navigationView.setRightComponent(horizontalComponentGroup);
        horizontalComponentGroup.setCaption("Horizontal straight to layout");

        c = new Button();
        c.setIcon(mailImage);

        navigationView.setLeftComponent(c);

        addComponent(navigationView);
        TestUtils.makeSmallTabletSize(navigationView);

    }

    private HorizontalButtonGroup getHorizontalGroup() {
        HorizontalButtonGroup horizontalComponentGroup = new HorizontalButtonGroup();
        horizontalComponentGroup.addComponent(new Button("First"));
        horizontalComponentGroup.addComponent(new Button("Another"));

        return horizontalComponentGroup;
    }

}
