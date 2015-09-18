package com.example.ratulaggarwal.schedulemessage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


/**
 * Created by ratulaggarwal on 9/8/15.
 */
public class Login_Home extends Activity {

    LoginButton loginbutton;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken, oldaccesstoken;
    Intent newmessage = new Intent("com.example.ratulaggarwal.schedulemessage.NEWMESSAGE");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_home);
        callbackManager = CallbackManager.Factory.create();

        int fullscreenUI = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE;

        getWindow().getDecorView().setSystemUiVisibility(fullscreenUI);

        loginbutton = (LoginButton) findViewById(R.id.login_button);

        loginbutton.setReadPermissions("user_friends");

        loginbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                oldaccesstoken = loginResult.getAccessToken();
                Toast.makeText(getApplicationContext(), "We are in.", Toast.LENGTH_LONG).show();
                startActivity(newmessage);
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getApplicationContext(), "We are kicked out.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(getApplicationContext(), "Something went wrong.. Like always.", Toast.LENGTH_LONG).show();
                AlertDialog dialog = new AlertDialog.Builder(Login_Home.this).create();
                dialog.setTitle("Exception Alert");
                dialog.setMessage(exception.toString());
                dialog.show();
            }
        });

        accessToken = AccessToken.getCurrentAccessToken();

        accessTokenTracker = new AccessTokenTracker() {

            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                currentAccessToken = accessToken;
                oldAccessToken = oldaccesstoken;
                tracker(currentAccessToken, oldAccessToken);
            }
        };

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
/*        System.out.println(requestCode);
        System.out.println(resultCode);
        System.out.println(data);  */
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tracker(oldaccesstoken, accessToken);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tracker(oldaccesstoken, accessToken);
    }

    public void tracker(AccessToken old, AccessToken current){
        if(current == old){
            startActivity(newmessage);
        }
    }
}
