package com.example.registrasi_validasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class signup extends AppCompatActivity {

    //variabel
    Button next, login;
    TextView titleText, slideText;

    //getData Data Variabel
    TextInputLayout fullName, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        //hooks for animation
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signin_btn);
        titleText = findViewById(R.id.signup_title_text);
        slideText = findViewById(R.id.signup_slide_text);

        //hook for getting data
        fullName = findViewById(R.id.signup_fullname);
        email = findViewById(R.id.signup_email);
        username = findViewById(R.id.signup_username);
        password = findViewById(R.id.signup_password);

    }

    public void callNextSignupScreen(View view) {

        if (!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()){
            return;
        }

        Intent intent = new Intent(this,SignUp2ndClass.class);

        //add share animation
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(next, "transition_next_btn");
        pairs[1] = new Pair<View, String>(login, "transition_signin_btn");
        pairs[2] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[3] = new Pair<View, String>(slideText, "transition_slide_text");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(signup.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }

    // Validation Functions

    private boolean validateFullName() {

        String val = fullName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullName.setError("Field can not  be empity");
            return false;
        }    else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateUsername() {

        String val = username.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            username.setError("Field can not  be empity");
            return false;
        }    else if (val.length()>20){
            username.setError("Username is too large!");
            return false;
        }
        else if (!val.matches(checkspaces)){
            username.setError("No white spaces are allowed!");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {

        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not  be empity");
            return false;
        }
        else if (!val.matches(checkEmail)){
            email.setError("Invalid email!");
            return false;
        }
        else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {

        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])"+        //at least 1 digit
                // "(?=.*[a-z])"+        //at least lower case letter
                // "(?=.*[A-Z])"+        //at least 1 upper case letter
                "(?=.*[a-aZ-Z])"+        //any letter
                "(?=.*[@#$%^&+=])"+        //at least 1 special character
                "(?=\\S+$)"+            //no white spaces
                ".{4,}"+                //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field can not  be empity");
            return false;
        }
        else if (!val.matches(checkPassword)){
            password.setError("Password should contain 4 characters!");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }


}