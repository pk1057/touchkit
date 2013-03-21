package com.vaadin.addon.touchkit.server;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.addon.touchkit.settings.TouchKitSettings;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;

/**
 * This servlet should be used by all TouchKit applications. It automatically
 * creates an instance of TouchKitSettings bound to the current
 * {@link VaadinService} in the {@link #servletInitialized()} method. The
 * {@link #servletInitialized()} method can be overridden to configure defaults
 * suitable for this web application.
 * <p>
 * If a TouchKit application cannot use this Servlet as its super class,
 * developers should manually create and bind a {@link TouchKitSettings}
 * instance.
 */
public class TouchKitServlet extends VaadinServlet {

    /** The TouchKitSettings instance used */
    private TouchKitSettings touchKitSettings;

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.server.VaadinServlet#servletInitialized()
     */
    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        touchKitSettings = new TouchKitSettings(getService());
    }

    /**
     * @return The {@link TouchKitSettings} instance bound to the
     *         {@link VaadinService} related to this servlet
     */
    public TouchKitSettings getTouchKitSettings() {
        return touchKitSettings;
    }

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null
                && request.getPathInfo().startsWith(
                        "/VAADIN/themes/touchkit/styles.css")) {
            serveDummyFile(response);
        } else {
            super.service(request, response);
        }
    }

    @Override
    protected void writeStaticResourceResponse(HttpServletRequest request,
            HttpServletResponse response, URL resourceUrl) throws IOException {
        String file = resourceUrl.getFile();
        if (file.endsWith(".manifest")) {
            response.setContentType("text/cache-manifest");
            response.setHeader("Cache-Control", "max-age=1, must-revalidate");
        }
        super.writeStaticResourceResponse(request, response, resourceUrl);
    }

    private void serveDummyFile(HttpServletResponse response)
            throws IOException {
        response.setContentType("text/css");
        response.setHeader("Cache-Control", "max-age=1000000");
        response.getOutputStream().write("\n".getBytes());
    }

}
