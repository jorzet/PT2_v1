package com.eeg.pt1_v1.services.webservice.Httpzoid;

/**
 * (c) Artur Sharipov
 */
public class NetworkAuthenticationException extends HttpzoidException {
    public NetworkAuthenticationException() {
        super("Network authentication required", NetworkError.AuthenticationRequired);
    }
}
