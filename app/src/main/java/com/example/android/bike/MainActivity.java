package com.example.android.bike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.*;
import com.facebook.login.LoginManager.*;
import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity {

    private LoginManager lm;
    private Button b;
    private CallbackManager callbackManager;
    private TextView tv,tv1;
    private EditText useret,passet2;
    private String user, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        useret = (EditText) findViewById(R.id.edittextemail);
        passet2 = (EditText) findViewById(R.id.edittextpass);
        initialize();
        loginManager();
        b=(Button) findViewById(R.id.login);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnLogin();
            }
        });
        
        tv1=(TextView) findViewById(R.id.textView);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Reg.class);
                startActivity(i);
            }
        });
    }

    private void initialize(){
        callbackManager = CallbackManager.Factory.create();
        tv = (TextView) findViewById(R.id.status);
    }

    public void loginManager(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                tv.setText("success!!!");
                b.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancel() {
                tv.setText("Cancelled");
            }

            @Override
            public void onError(FacebookException exception) {
                tv.setText("error");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    public void OnLogin(){
        user=useret.getText().toString();
        pass=passet2.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,user,pass);
    }

    }

