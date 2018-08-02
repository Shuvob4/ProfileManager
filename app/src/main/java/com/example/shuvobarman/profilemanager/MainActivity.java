package com.example.shuvobarman.profilemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button_Start, button_Stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_Start = (Button) findViewById(R.id.button_start);
        button_Stop = (Button) findViewById(R.id.button_stop);

    }

    public void Start(View view) {
        startService(new Intent(getBaseContext(), SmartService.class));
    }


    public void Stop(View view) {
        stopService(new Intent(getBaseContext(), SmartService.class));
    }
}
