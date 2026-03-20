package com.aiwa.remote.toast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class ToastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        final String message = intent.getStringExtra("message");
        if (message == null || message.isEmpty()) return;

        final int duration = intent.getBooleanExtra("long", false)
                ? Toast.LENGTH_LONG
                : Toast.LENGTH_SHORT;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, duration).show();
            }
        });
    }
}
