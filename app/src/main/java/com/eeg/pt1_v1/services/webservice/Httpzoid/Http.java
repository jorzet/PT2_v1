package com.eeg.pt1_v1.services.webservice.Httpzoid;

/**
 * Http request creator.
 * Malformed URL will throw runtime exception.
 *
 * (c) Artur Sharipov
 */
public interface Http {
    public HttpRequest get(String url);
    public HttpRequest post(String url);
    public HttpRequest put(String url);
    public HttpRequest delete(String url);
    public HttpRequest request(String url, String method);
}

