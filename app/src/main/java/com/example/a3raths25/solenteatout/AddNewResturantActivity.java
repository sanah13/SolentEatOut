package com.example.a3raths25.solenteatout;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Intent;
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

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.AddNewResturant) {
            Intent intent = new Intent(this, AddNewResturantActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        if (item.getItemId() == R.id.preferences) {
            Intent intent = new Intent(this, PrefsActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }

        if (item.getItemId() == R.id.LoadFromFile) {
            Intent intent = new Intent(this, LoadFromFileActivity.class);
            startActivityForResult(intent, 2);
            return true;
        }
        if (item.getItemId() == R.id.LoadFromWeb) {
            Intent intent = new Intent(this, LoadFromFileActivity.class);
            startActivityForResult(intent, 3);
            return true;
        }
        return false;
    }

    public void onClick(View view) {

        EditText name = (EditText) findViewById(R.id.et1);
        EditText address = (EditText) findViewById(R.id.et2);
        EditText cuisine = (EditText) findViewById(R.id.et3);
        EditText rating = (EditText) findViewById(R.id.et4);

        Bundle bundle = new Bundle();

        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}



