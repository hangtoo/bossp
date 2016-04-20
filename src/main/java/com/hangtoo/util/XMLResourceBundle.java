package com.hangtoo.util;

import java.io.IOException;
import java.io.InputStream;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;


public class XMLResourceBundle extends ResourceBundle {
    private Properties props;

    XMLResourceBundle(InputStream stream) throws IOException {
        props = new Properties();
        props.loadFromXML(stream);
    }

    @Override
    protected Object handleGetObject(String key) {
        return props.getProperty(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return new Enumeration() {
                Iterator i = props.keySet().iterator();

                public boolean hasMoreElements() {
                    return (i.hasNext());
                }

                public Object nextElement() {
                    return i.next();
                }
            };
    }
}
