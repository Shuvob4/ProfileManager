package com.example.shuvobarman.profilemanager;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

/**
 * Created by Shuvo Barman on 5/2/2017.
 */

public class ProfileManager {

    private AudioManager audManager;
    Context myContext;

    public ProfileManager(Context myContext) {
        this.myContext = myContext;
        audManager = (AudioManager)myContext.getSystemService(Context.AUDIO_SERVICE);

    }

    public void home() {
        if(audManager.getRingerMode()!= AudioManager.RINGER_MODE_NORMAL)
            audManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        Log.d("profile", "home");
    }

    public void pocket() {
        if(audManager.getRingerMode()!= AudioManager.RINGER_MODE_VIBRATE)
            audManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        Log.d("profile", "pocket");
    }

    public void silent() {
        if(audManager.getRingerMode()!= AudioManager.RINGER_MODE_SILENT)
            audManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        Log.d("profile", "silent");
    }
}
