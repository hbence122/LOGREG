package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editUserName, editUserPass;
    Button btnLogin, btnRegister;
    dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editUserName.getText().toString();
                String felhasz=editUserName.getText().toString();
                String password=editUserPass.getText().toString();
                Boolean checkUser = dbhelper.checkUser(email,felhasz, password);
                if (checkUser==true)
                {
                    Intent intent=new Intent(MainActivity.this,LoggedIn.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"Sikeres bejelentkezés",Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(),"Sikertelen bejelentkezés",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void init(){
        editUserName=(EditText) findViewById(R.id.editUserName);
        editUserPass=(EditText) findViewById(R.id.editUserPass);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        dbhelper =new dbhelper(this);
    }
}
