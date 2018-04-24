package com.example.a3raths25.solenteatout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Intent;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 3RATHS25 on 19/04/2018.
 */

public class AddNewResturantActivity extends AppCompatActivity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_resturant);

        Button go_button = (Button) findViewById(R.id.btn1);
        go_button.setOnClickListener(this);
    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }


    public void onClick(View view) {}

    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.getString("filename");
        if (item.getItemId() == R.id.save) {

            try {
                PrintWriter pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() +"/notepad.txt"));
                EditText et1 = (EditText) findViewById(R.id.et1);
                pw.write(et1.getText().toString());
                pw.close();
            } catch (IOException e) {
                new AlertDialog.Builder(this).setPositiveButton("OK", null).
                        setMessage("ERROR: " + e).show();
            }
            return true;
        }
}



