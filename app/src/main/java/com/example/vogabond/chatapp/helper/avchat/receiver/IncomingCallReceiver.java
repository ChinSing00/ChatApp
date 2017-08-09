package com.example.vogabond.chatapp.helper.avchat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * Created by MSI on 2017/8/8.
 */

public class IncomingCallReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TelephonyManager.ACTION_PHONE_STATE_CHANGED.equals(action)) {
            final String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            PhoneCallStateObserver.getInstance().onCallStateChanged(state);
        }
    }
}
