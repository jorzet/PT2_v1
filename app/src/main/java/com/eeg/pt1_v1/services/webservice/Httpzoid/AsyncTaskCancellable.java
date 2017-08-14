package com.eeg.pt1_v1.services.webservice.Httpzoid;

import android.os.AsyncTask;

public class AsyncTaskCancellable implements Cancellable {
    private AsyncTask task;

    public AsyncTaskCancellable(AsyncTask task) {
        this.task = task;
    }

    public void cancel() {
        task.cancel(false);
    }
}
