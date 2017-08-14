package com.eeg.pt1_v1.services.webservice.Httpzoid;

import java.util.List;
import java.util.Map;

/**
 * (c) Artur Sharipov
 */
public class HttpDataResponse extends HttpResponse {
    private Object data;

    public HttpDataResponse(Object data, int code, Map<String, List<String>> headers) {
        super(code, headers);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
