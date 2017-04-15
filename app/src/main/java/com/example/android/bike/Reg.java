package com.example.android.bike;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reg extends AppCompatActivity {

    private TextView tv;
    private Button btn;
    private EditText et,et2,et3,et4,et5;
    private String p1,p2,fname,lname,email;
    private String Name = "MyPrefs";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        tv = (TextView) findViewById(R.id.regid);
        tv.setTextColor(ContextCompat.getColor(this,R.color.colormtext));
        et = (EditText) findViewById(R.id.editText4);
        et2 = (EditText) findViewById(R.id.editText5);
        et3 = (EditText) findViewById(R.id.editText6);
        et4 = (EditText) findViewById(R.id.editText7);
        et5 = (EditText) findViewById(R.id.editText8);
        btn = (Button) findViewById(R.id.reg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){
        sharedPreferences = getSharedPreferences(Name, Context.MODE_PRIVATE);
        fname = et.getText().toString();
        lname = et2.getText().toString();
        email = et3.getText().toString();
        p1 = et4.getText().toString();
        p2 = et5.getText().toString();
        if(p2!=null && fname != null && lname != null && email != null && p1 != null){
            if (p1.equals(p2)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fName",fname);
            editor.putString("id",email);
            editor.putString("lname",lname);
            editor.putString("pass",p1);
            editor.commit();
            Toast.makeText(this,""+sharedPreferences.getAll(),Toast.LENGTH_LONG).show();}
            else{
                Toast.makeText(this,"check for equality of passwords",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"check for any incomplete/wrong fields",Toast.LENGTH_SHORT).show();
        }
        String type = "register";
        String s1 = fname+" "+lname;
        String s2 = email+" "+p1;
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,s1,s2);
    }

}
