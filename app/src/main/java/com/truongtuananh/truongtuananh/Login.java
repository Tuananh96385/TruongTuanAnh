package com.truongtuananh.truongtuananh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.firebase.ui.auth.AuthUI;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity {
//    List<AuthUI.IdpConfig> providers;
    EditText getTextUsername;
    EditText getTextPassword;
    Button getButtonLogin;
    Button getButtonRegister;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        providers = Arrays.asList(
//                new AuthUI.IdpConfig.GoogleBuilder().build(),
//                new AuthUI.IdpConfig.PhoneBuilder().build(),
//                new  AuthUI.IdpConfig.FacebookBuilder().build()
//        );

        db = new Database(this);
        getTextUsername = (EditText) findViewById(R.id.edittext_username);
        getTextPassword = (EditText)findViewById(R.id.edittext_password);
        getButtonLogin = (Button)findViewById(R.id.btn_login);
        getButtonRegister = (Button) findViewById(R.id.btn_register);
        getButtonRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent registerIntent  = new Intent(Login.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        getButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = getTextUsername.getText().toString().trim();
                String password = getTextPassword.getText().toString().trim();
                Boolean res = db.usernamePassword(username,password);
                if(res==true)
                {
                    Intent PageHome  = new Intent(Login.this,MainActivity.class);
                    startActivity(PageHome);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}