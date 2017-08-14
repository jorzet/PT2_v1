package com.eeg.pt1_v1.services.webservice.Httpzoid;

/**
 * Network errors
 *
 * (c) Artur Sharipov
 */
public enum NetworkError {
    Offline,
    /**
     * Network authentication requested (web-form)
     */
    AuthenticationRequired,
    /**
     * Unsupported HTTP method requested
     */
    UnsupportedMethod,
    /**
     * Request timeout
     */
    Timeout, /**
     * Unknown error, post an issue with logs if you encounter it.
     */
    Unknown
}
