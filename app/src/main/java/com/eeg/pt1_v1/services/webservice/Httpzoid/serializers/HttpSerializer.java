package com.eeg.pt1_v1.services.webservice.Httpzoid.serializers;

/**
 * (c) Artur Sharipov
 */
public interface HttpSerializer {
    public String getContentType();
    public String serialize(Object object);
    public Object deserialize(String value, Class type);
}
