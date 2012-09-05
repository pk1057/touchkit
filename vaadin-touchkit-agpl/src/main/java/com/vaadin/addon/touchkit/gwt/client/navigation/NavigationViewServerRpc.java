package com.vaadin.addon.touchkit.gwt.client.navigation;

import com.vaadin.shared.annotations.Delayed;
import com.vaadin.shared.communication.ServerRpc;

public interface NavigationViewServerRpc extends ServerRpc {

    @Delayed(lastonly=true)
    public void updateScrollPosition(int position);

}
