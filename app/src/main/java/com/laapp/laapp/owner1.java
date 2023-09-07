package com.laapp.laapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class owner1 extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText phoneNumber;
    EditText pincode;
    EditText address1;
    EditText address2;
    EditText address3;
    EditText city;
    EditText state;
    EditText password;
    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner1);
            name = findViewById(R.id.name);
            email = findViewById(R.id.email);
            phoneNumber = findViewById(R.id.phoneNumber);
            pincode = findViewById(R.id.pincode);
            address1 = findViewById(R.id.address1);
            address2 = findViewById(R.id.address2);
            address3 = findViewById(R.id.address3);
            city = findViewById(R.id.city);
            state = findViewById(R.id.state);
            password = findViewById(R.id.password);
            login = findViewById(R.id.login);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String semail = email.getText().toString();
                    String sphoneNumber = phoneNumber.getText().toString();
                    String spincode = pincode.getText().toString();
                    String saddress1 = address1.getText().toString();
                    String saddress2 = address2.getText().toString();
                    String saddress3 = address3.getText().toString();
                    String scity = city.getText().toString();
                    String sstate = state.getText().toString();
                    String spassword = password.getText().toString();
                    String slogin = login.getText().toString();
                }
            });

        }
    }
}