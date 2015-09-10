package com.example.ratulaggarwal.schedulemessage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;

/**
 * Created by ratulaggarwal on 9/9/15.
 */
public class NewMessage extends Activity implements View.OnClickListener{

    EditText to,message;
    TextView addimage;
    Button save;
    Intent imageadder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmessage);
        to = (EditText)findViewById(R.id.editTextto);
        message = (EditText)findViewById(R.id.editTextmessage);
        save = (Button)findViewById(R.id.buttonsave);
        addimage = (TextView)findViewById(R.id.textViewaddimage);
        save.setOnClickListener(this);
        addimage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.buttonsave:
                break;

            case R.id.textViewaddimage:
                imageadder = new Intent("com.example.ratulaggarwal.schedulemessage.ADDIMAGE");
                startActivity(imageadder);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}
