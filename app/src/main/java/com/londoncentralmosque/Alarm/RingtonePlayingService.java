package com.londoncentralmosque.Alarm;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.londoncentralmosque.AlarmActivity;

/**
 * Created by Android Dev on 6/20/2017.
 */

public class RingtonePlayingService extends Service {

    private Ringtone ringtone;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//
        Intent intent2 = new Intent (this, AlarmActivity.class);
//        intent.putExtra("vid", vid);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        int flag=flags;
        Log.d("this Flags:","flagin:"+flag);
        startActivity(intent2);


        //     Intent intent1=new Intent(this,AlarmActivity.class);
        //  startActivity(intent1);
//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        Log.d("Time", "Alarm running");
//
//
//        if (uri == null) {
//            uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }
//
//        ringtone = RingtoneManager.getRingtone(this, uri);
//
//        ringtone.play();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ringtone.stop();
    }
}
