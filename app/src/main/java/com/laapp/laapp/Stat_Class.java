package com.laapp.laapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Stat_Class {

    @SuppressLint("NewApi")
    public  Connection ConnectionClass1()
    {
        Connection con=null;
        String ip="162.222.225.88";
        String port="1433";
        String Classes = "net.sourceforge.jtds.jdbc.Driver";
        String database = "LAAPP";
        String username="LAAPP";
        String password="laapp@2023";
        String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //    String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";User=" + username + ";password=" + password + ";";
            con = DriverManager.getConnection(url,username,password);
            Log.e("Connection Success","open");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }






        return con;
    }



//    void getIp()
//    {
//
//        String url = "https://ipapi.co/json";
//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url) // Replace with your API endpoint
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                String responseData = response.body().string();
//                JSONObject jsonObject = new JSONObject(responseData);
//                String ip = jsonObject.getString("ip");
//                String lal = jsonObject.getDouble("latitude") + "";
//                String lon = jsonObject.getDouble("longitude") + "";
//                String cc = jsonObject.getString("country_code");
//                Log.v("ip", ip);
//
//
//
//
//            } else {
//                // Handle unsuccessful response (e.g., non-200 status codes)
//                Toast.makeText(getApplicationContext(), "Error1", Toast.LENGTH_SHORT).show();
//            }
//        } catch (IOException e) {
//            Toast.makeText(getApplicationContext(), "Error2", Toast.LENGTH_SHORT).show();
//
//            e.printStackTrace();
//            // Handle the exception
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        Toast.makeText(getApplicationContext(), ip1.getText().toString(), Toast.LENGTH_SHORT).show();
//
//    }


}
