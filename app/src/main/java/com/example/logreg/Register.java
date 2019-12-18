package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText editEmail, editFelhasz, editJelszo, editNev;
    Button btnRegisztracio, btnVissza;
    dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();


        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String email = editEmail.getText().toString();
                Boolean checkemail = dbhelper.checkemail(email);

                if (!hasFocus){
                    if (checkemail == false)
                    editEmail.setTextColor(getResources().getColor(R.color.colorrossz));

                    else editEmail.setTextColor(getResources().getColor(R.color.colorjo));
                }
                if (!hasFocus){
                    if (!email.contains("@")){
                        editEmail.setError("Nem email formátum");
                    }
                }

            }
        });

        editFelhasz.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String email = editEmail.getText().toString();
                String felhasz= editFelhasz.getText().toString();
                String jelszo = editJelszo.getText().toString();
                String nev = editNev.getText().toString();
                Boolean checkFelhasz = dbhelper.checkFelhasz(felhasz);

                if(!hasFocus){
                    if (checkFelhasz == false){
                        editFelhasz.setTextColor(getResources().getColor(R.color.colorrossz));
                    }
                    else editFelhasz.setTextColor(getResources().getColor(R.color.colorjo));
                }

            }
        });

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = editEmail.getText().toString();
                String felhasz= editFelhasz.getText().toString();
                String jelszo = editJelszo.getText().toString();
                String nev = editNev.getText().toString();

                if (!email.equals("") && !felhasz.equals("") && !jelszo.equals("") && !nev.equals("")){
                    btnRegisztracio.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editFelhasz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String email = editEmail.getText().toString();
                String felhasz= editFelhasz.getText().toString();
                String jelszo = editJelszo.getText().toString();
                String nev = editNev.getText().toString();

                if (!email.equals("") && !felhasz.equals("") && !jelszo.equals("") && !nev.equals("")){
                    btnRegisztracio.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editJelszo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String email = editEmail.getText().toString();
                String felhasz= editFelhasz.getText().toString();
                String jelszo = editJelszo.getText().toString();
                String nev = editNev.getText().toString();

                if (!email.equals("") && !felhasz.equals("") && !jelszo.equals("") && !nev.equals("")){
                    btnRegisztracio.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editNev.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String email = editEmail.getText().toString();
                String felhasz= editFelhasz.getText().toString();
                String jelszo = editJelszo.getText().toString();
                String nev = editNev.getText().toString();

                if (!email.equals("") && !felhasz.equals("") && !jelszo.equals("") && !nev.equals("")){
                    btnRegisztracio.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnRegisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String felhasz= editFelhasz.getText().toString();
                String jelszo = editJelszo.getText().toString();
                String nev = editNev.getText().toString();
                Boolean checkemail = dbhelper.checkemail(email);
                Boolean checkFelhasz = dbhelper.checkFelhasz(felhasz);
                if (email.equals("") || felhasz.equals("") || jelszo.equals("") || nev.equals("")){
                    Toast.makeText(getApplicationContext(), "Mindegyik mezőt ki kell tölteni",Toast.LENGTH_SHORT).show();
                }

                else if (checkemail == false){
                    Toast.makeText(getApplicationContext(),"Ez az email cím már foglalt",Toast.LENGTH_SHORT).show();

                }

                else if (!nev.contains(" ")){
                    Toast.makeText(getApplicationContext(),"A teljes nevedet add meg!",Toast.LENGTH_SHORT).show();
                }

                else if (checkFelhasz == false){
                    Toast.makeText(getApplicationContext(),"Ez a felhasználónév már foglalt",Toast.LENGTH_SHORT).show();
                }

                else if (!nev.contains("@")){
                    Toast.makeText(getApplicationContext(),"Nem megfelelő email",Toast.LENGTH_SHORT).show();
                }

                else{

                    if (checkemail == true && checkFelhasz == true) {
                        Boolean adatRogzites = dbhelper.adatRogzites(email, felhasz, jelszo, nev);
                        if (adatRogzites == true) {
                            Toast.makeText(getApplicationContext(), "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }
        });
    }

    public void init(){
        editEmail=(EditText) findViewById(R.id.editEmail);
        editFelhasz=(EditText) findViewById(R.id.editFelhasz);
        editJelszo=(EditText) findViewById(R.id.editJelszo);
        editNev=(EditText) findViewById(R.id.editNev);
        btnRegisztracio=(Button) findViewById(R.id.btnRegisztracio);
        btnRegisztracio.setEnabled(false);
        btnVissza=(Button) findViewById(R.id.btnVissza);
        dbhelper =new dbhelper(this);
    }

    public void regGomb(){

        String email = editEmail.getText().toString();
        String felhasz= editFelhasz.getText().toString();
        String jelszo = editJelszo.getText().toString();
        String nev = editNev.getText().toString();
        if (email.equals("") && felhasz.equals("") && jelszo.equals("") && nev.equals("")){
            btnRegisztracio.setEnabled(false);
        }
        else  btnRegisztracio.setEnabled(true);
    }
}
