package org.sweatshop.injection;

import org.glassfish.jersey.process.internal.RequestScoped;

@RequestScoped
public class StringHolder {

    private final String string;

    public StringHolder(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}