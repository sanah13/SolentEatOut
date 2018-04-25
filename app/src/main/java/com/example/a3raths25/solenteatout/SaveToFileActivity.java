package com.example.a3raths25.solenteatout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.a3raths25.solenteatout.R.styleable.MenuItem;

/**
 * Created by 3RATHS25 on 25/04/2018.
 */

public class SaveToFileActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();

                String name = extras.getString("name");
                String address = extras.getString("address");
                String cuisine = extras.getString("cuisine");
                String rating = extras.getString("rating");

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                prefs.getString("NewResturant");
                {
                    try {
                        PrintWriter pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/NewResturants.txt"));
                        pw.write(name);
                        pw.write(address);
                        pw.write(cuisine);
                        pw.write(rating);
                        pw.close();

                    } catch (IOException e) {
                        new AlertDialog.Builder(this).setPositiveButton("OK", null).
                                setMessage("ERROR: " + e).show();
                    }
                    return true;
                }
            }
        }
    }

    @Override
    public void onClick(View view) {

    }
}