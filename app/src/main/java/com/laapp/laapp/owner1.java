package com.laapp.laapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.Task;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class owner1 extends AppCompatActivity {
    private static final int LOCATION_REQUEST_CODE = 1;
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
    EditText ip1;
    private double latitude;
    private double longitude;

    private LocationManager locationManager;
    private LocationListener locationListener;

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
        ip1 = findViewById(R.id.ip11);


        if (ContextCompat.checkSelfPermission(owner1.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(owner1.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(owner1.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(owner1.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        checkLocationSettings();


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sname = name.getText().toString();
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
                    String spin = pincode.getText().toString();
                    getLocation();

                    try {
                        Connection con = ConnectionClass1();
                        if (con != null) {
                            CallableStatement callableStatement = con.prepareCall("{call dbo.SP_LoginReg(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

                            callableStatement.setString(1, sname);
                            callableStatement.setString(2, semail);
                            callableStatement.setString(3, sphoneNumber);
                            callableStatement.setString(4, "91");
                            callableStatement.setString(5, saddress1);
                            callableStatement.setString(6, saddress2);
                            callableStatement.setString(7, "null");
                            callableStatement.setString(8, spincode);
                            callableStatement.setString(9, scity);
                            callableStatement.setString(10, "19");
                            callableStatement.setString(11, "IND");
                            callableStatement.setString(12, "LU");
                            callableStatement.setString(13, spassword);
                            callableStatement.setString(14, "2401:4900:1c85:986a:7503:dbae:7ef2:c557");
                            callableStatement.setString(15, ""+latitude);
                            callableStatement.setString(16, ""+longitude);

                            // Register the output parameter.
                            callableStatement.registerOutParameter(17, Types.VARCHAR);

                            // Execute the callable statement.
                            callableStatement.execute();

                            // Get the output parameter value.
                            String status = callableStatement.getString(17);

                            // Close the callable statement and connection.
                            callableStatement.close();
                            con.close();

                            // Print the output parameter value.
                            System.out.println("STATUS: " + status);
                            Log.v("Status", status);
                            Toast.makeText(owner1.this, status, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.v("Error", "Null Connection");
                        }

                    } catch (Exception e) {
                        Log.e("Error1", e.getMessage());
                    }
                }
            });

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(owner1.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void checkLocationSettings() {
        LocationRequest locationRequest = LocationRequest.create();
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        Task<LocationSettingsResponse> result =
                LocationServices.getSettingsClient(this).checkLocationSettings(builder.build());

        result.addOnCompleteListener(task -> {
            try {
                task.getResult(ApiException.class);
                // Location settings are satisfied, and user has enabled location services
            } catch (ApiException exception) {
                switch (exception.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed by showing the user a dialog
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult()
                            ResolvableApiException resolvable = (ResolvableApiException) exception;
                            resolvable.startResolutionForResult(this, LOCATION_REQUEST_CODE);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied, and there's no way to fix them
                        // You can inform the user or take appropriate action here
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // User enabled location services
                getLocation();
            } else {
                checkLocationSettings();
            }
        }
    }

    private void getLocation() {
         FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null) {
                    Location location = locationResult.getLastLocation();
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    // You can also update UI elements or perform other actions here
                    updateUIWithLocation(latitude, longitude);
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    // Function to update UI elements or perform actions with the obtained location
    private void updateUIWithLocation(double lat, double longi) {
        // You can access latitude and longitude here
        // For example, you can update TextViews, send them to a server, etc.
        Toast.makeText(this, ""+latitude+","+longitude, Toast.LENGTH_SHORT).show();
    }



    @SuppressLint("NewApi")
    public Connection ConnectionClass1() {
        Connection con = null;
        String ip = "162.222.225.88";
        String port = "1433";
        String Classes = "net.sourceforge.jtds.jdbc.Driver";
        String database = "LAAPP";
        String username = "LAAPP";
        String password = "laapp@2023";
        String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database;


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //    String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";User=" + username + ";password=" + password + ";";
            con = DriverManager.getConnection(url, username, password);
            Log.e("Connection Success", "open");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}







