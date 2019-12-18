package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedIn extends AppCompatActivity {

    TextView txtFelhasznalo;
    Button btnLogout;
    dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();

        txtFelhasznalo.setText("Üdvözöllek ");

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoggedIn.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        txtFelhasznalo=(TextView) findViewById(R.id.txtFelhasznalo);
        btnLogout=(Button) findViewById(R.id.btnLogout);
        dbhelper =new dbhelper(this);
    }
}
