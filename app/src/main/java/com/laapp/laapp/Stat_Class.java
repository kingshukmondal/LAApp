package com.laapp.laapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Stat_Class {

    @SuppressLint("NewApi")
    public static  Connection ConnectionClass1()
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
}
