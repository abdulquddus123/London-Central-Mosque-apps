package com.londoncentralmosque.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Android Dev on 6/20/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context,Intent intent) {

        Intent intent1 = new Intent(context, RingtonePlayingService.class);

        context.startService(intent1);
    }
}
