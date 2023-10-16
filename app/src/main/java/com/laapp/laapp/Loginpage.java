package com.laapp.laapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Loginpage extends AppCompatActivity {


    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        username=findViewById(R.id.uname);
        password=findViewById(R.id.pd);


    }



    public void click(View view) {
   //     startActivity(new Intent(getApplicationContext(),owner1.class));

        String name=username.getText().toString();
        String pass=password.getText().toString();

        CheckLoginTask c1= new CheckLoginTask(name,pass);
        String ans=c1.doInBackground();
        Log.v("ans",ans);




    }




    public class CheckLoginTask extends AsyncTask<String, Void, String> {



        private String username;
        private String password;

        public CheckLoginTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected String doInBackground(String... params) {
            Connection connection = null;
            CallableStatement callableStatement = null;
            ResultSet resultSet = null;

            try {
                // Create a new JDBC connection to the database.
                connection = new Stat_Class().ConnectionClass1();

                // Create a new CallableStatement object for the SP_CheckLogin stored procedure.
                callableStatement = connection.prepareCall("{CALL dbo.SP_CheckLogin(?, ?, ?, ?, ?)}");

                // Set the parameters of the CallableStatement object with the username and password that the user has entered.
                callableStatement.setString(1, username);
                callableStatement.setString(2, password);
                callableStatement.registerOutParameter(3, Types.INTEGER);
                callableStatement.registerOutParameter(4, Types.VARCHAR);
                callableStatement.registerOutParameter(5, Types.INTEGER);
                callableStatement.registerOutParameter(6, Types.VARCHAR);

                // Execute the CallableStatement object.
                callableStatement.execute();

                // Get the output parameters from the CallableStatement object.
                int errorCode = callableStatement.getInt(3);
                String errorMessage = callableStatement.getString(4);
                int loginId = callableStatement.getInt(5);
                String sessionId = callableStatement.getString(6);

                // Close the CallableStatement object and the JDBC connection.
                callableStatement.close();
                connection.close();

                // Return the output parameters to the onPostExecute() method.
                return errorCode + "," + errorMessage + "," + loginId + "," + sessionId;
            } catch (SQLException e) {
                e.printStackTrace();
                return "ERROR," + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            String[] parts = result.split(",");
            int errorCode = Integer.parseInt(parts[0]);
            String errorMessage = parts[1];
            int loginId = Integer.parseInt(parts[2]);
            String sessionId = parts[3];

            if (errorCode == 0) {
                // Login was successful

                // Do something with the loginId and sessionId values.
            } else {
                // Login failed.
                // Display the errorMessage to the user.
            }
        }
    }
}