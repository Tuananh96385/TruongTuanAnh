package com.truongtuananh.truongtuananh;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText getTextUsername;
    EditText getTextPassword;
    EditText getCnfPassword;
    Button getButtonRegister;
    Button getButtonLogin;
    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new Database(this);
        getTextUsername = (EditText) findViewById(R.id.edittext_username);
        getTextPassword = (EditText)findViewById(R.id.edittext_password);
        getCnfPassword = (EditText) findViewById(R.id.edittext_cnf_password);
        getButtonRegister = (Button) findViewById(R.id.btn_register);
        getButtonLogin = (Button) findViewById(R.id.btn_login);

        getButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ReturnLogin  = new Intent(RegisterActivity.this,Login.class);
                startActivity(ReturnLogin);
            }
        });
       getButtonRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String username = getTextUsername.getText().toString().trim();
               String password = getTextPassword.getText().toString().trim();
               String cnf_password = getCnfPassword.getText().toString().trim();

               if (username.equals("") || password.equals("") || cnf_password.equals("")){
                   Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
               }else {
                   if (password.equals(cnf_password)){
                       Boolean checkusernamedb = db.checkusername(username);
                       if (checkusernamedb==true){
                           Boolean insert = db.insert(username,password);
                           if (insert==true){
                               Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_SHORT).show();
                           }
                       }
                       else {
                           Toast.makeText(getApplicationContext(),"Username Already exists",Toast.LENGTH_SHORT).show();
                       }
                   }
                   Toast.makeText(getApplicationContext(),"Password is not matching",Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}