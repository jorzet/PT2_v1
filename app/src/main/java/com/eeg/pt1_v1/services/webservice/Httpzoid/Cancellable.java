package com.eeg.pt1_v1.services.webservice.Httpzoid;

/**
 * (c) Artur Sharipov
 */
public interface Cancellable {
    public static final Cancellable Empty = new Cancellable() {
        @Override
        public void cancel() {
        }
    };
    public void cancel();
}

