package com.example.shuvobarman.profilemanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Shuvo Barman on 5/2/2017.
 */

public class SmartService extends Service {

    EventHandler em = null;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        em = new EventHandler(this);
        em.startEventHandler();
        Toast.makeText(this, "Service started", Toast.LENGTH_LONG).show();
        Log.d("service", "on start");
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        em.stopEventHandler();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        Log.d("service", "on destroy");
    }

}