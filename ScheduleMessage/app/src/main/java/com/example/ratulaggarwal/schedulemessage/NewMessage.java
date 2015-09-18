package com.example.ratulaggarwal.schedulemessage;

import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.appevents.AppEventsLogger;

/**
 * Created by ratulaggarwal on 9/9/15.
 */
public class NewMessage extends FragmentActivity implements View.OnClickListener{

    EditText to,message;
    TextView addimage;
    Button save;
    Intent imageadder;
    CheckBox sendlater;
    DialogFragment date, time;
    DatePickerFragment datedialog;
    TimePickerFragment timedialog;

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
        sendlater = (CheckBox)findViewById(R.id.checkboxsendlater);
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

    public void onCheckboxClicked (View view){
        boolean checked = ((CheckBox) view).isChecked();

        if(checked){
            //show the date and time picker dialog fragment
            showDatePickerDialog(view);
            showTimePickerDialog(view);
        }
    }

    public void showTimePickerDialog (View v){
        time = new TimePickerFragment();
        time.show(getSupportFragmentManager(), "timePicker");
    }


    public void showDatePickerDialog (View v){
        date = new DatePickerFragment();
        date.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Ok! You just crossed the line.", Toast.LENGTH_LONG).show();
                finish();
                break;

            case R.id.action_logout:

                break;
        }

        return true;
    }

}
