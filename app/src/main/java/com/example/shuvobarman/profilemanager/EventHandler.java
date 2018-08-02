package com.example.shuvobarman.profilemanager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by Shuvo Barman on 5/2/2017.
 */

public class EventHandler implements SensorEventListener {

    private Sensor proximity;
    private Sensor light;
    private Sensor accelerometer;
    private SensorManager sManager;
    private boolean inFrontObject=false;
    private boolean faceDown=false;
    private boolean dark=false;
    private boolean shaking=false;
    private float lastX=0,lastY=0,lastZ=0;
    ProfileManager pm = null;
    Context myContext;

    public EventHandler(Context myContext) {
        this.myContext = myContext;
        pm = new ProfileManager(this.myContext);
        init();
    }

    public void init()
    {
        sManager = (SensorManager)myContext.getSystemService(Context.SENSOR_SERVICE);
        proximity = sManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        light = sManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelerometer = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.d("event", " init function");
    }

    public void startEventHandler()
    {
        sManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("event", "start event");
    }

    public void stopEventHandler()
    {
        sManager.unregisterListener(this);
        Log.d("event", "stop event");
    }

    public void onSensorChanged(SensorEvent sEvent) {
        Sensor changedSensor = sEvent.sensor;
        if(changedSensor.getType() == Sensor.TYPE_PROXIMITY){
            if(sEvent.values[0]==0)
                inFrontObject = true;
            else
                inFrontObject = false;
        }
        if(changedSensor.getType() == Sensor.TYPE_LIGHT){
            if(sEvent.values[0]<=4 )
                dark = true;
            else
                dark = false;
        }
        if (changedSensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x,y,z,initialX,initialY,initialZ;
            x = sEvent.values[0];
            y = sEvent.values[1];
            z = sEvent.values[2];
            initialX = lastX;
            initialY = lastY;
            initialZ = lastZ;
            if((x>initialX+5 || x<initialX-5) || (y>initialY+5 || y<initialY-5) || (z>initialZ+5 || z<initialZ-5)){
                lastX=x;
                lastY=y;
                lastZ=z;
                shaking = true;
            }
            else
            {
                shaking = false;
            }
            if(z<-5)
                faceDown = true;
            else
                faceDown = false;

        }
        if(faceDown == false && inFrontObject == false && dark==false)
        {
            //Home Mode

            pm.home();
        }
        else if(faceDown == true && inFrontObject == true && dark==true && shaking == false)
        {
            //Silent Mode
            pm.silent();
        }
        else if(shaking == true && inFrontObject == true && dark==true)
        {
            //Pocket Mode
            pm.pocket();
        }
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

}