package org.sweatshop.injection;

import javax.inject.Inject;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

@Provider
public final class StartupListener implements Feature {

    private final ServiceLocator serviceLocator;

    @Inject
    public StartupListener(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public boolean configure(FeatureContext context) {
        ServiceLocatorUtilities.bind(serviceLocator, new AbstractBinder() {
            @Override
            protected void configure() {
                bind(new StringHolder("injected string")).to(StringHolder.class);
            }
        });
        return true;
    }

}