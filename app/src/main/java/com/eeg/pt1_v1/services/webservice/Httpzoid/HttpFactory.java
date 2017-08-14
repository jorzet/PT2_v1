package com.eeg.pt1_v1.services.webservice.Httpzoid;

import android.content.Context;
import android.net.ConnectivityManager;

import com.eeg.pt1_v1.services.webservice.Httpzoid.serializers.JsonHttpSerializer;

/**
 * (c) Artur Sharipov
 */
public class HttpFactory {
    public static Http create(Context context) {
        return new HttpUrlConnection(new JsonHttpSerializer(),
                new NetworkImpl((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)));
    }
}
