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

import static android.R.attr.name;
import static com.example.a3raths25.solenteatout.R.id.et1;
import static com.example.a3raths25.solenteatout.R.id.et2;
import static com.example.a3raths25.solenteatout.R.id.et3;
import static com.example.a3raths25.solenteatout.R.id.et4;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public void onClick(View v) {
        String name  = ((EditText)findViewById(et1)).getText().toString();
        String address  = ((EditText)findViewById(et2)).getText().toString();
        String cuisine  = ((EditText)findViewById(et3)).getText().toString();
        String rating  = ((EditText)findViewById(et4)).getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("address",address);
        bundle.putString("cuisine",cuisine);
        bundle.putString("rating",rating);

        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        prefs.getString("NewResturant");{
//                try {
//                    PrintWriter pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/NewResturants.txt"));
//                    EditText name = (EditText) findViewById(et1);
//                    EditText address = (EditText) findViewById(et2);
//                    EditText cuisine = (EditText) findViewById(et3);
//                    EditText rating = (EditText) findViewById(et4);
//                    pw.write(name.getText().toString());
//                    pw.write(address.getText().toString());
//                    pw.write(cuisine.getText().toString());
//                    pw.write(rating.getText().toString());
//                    pw.close();
//                } catch (IOException e) {
//                    new AlertDialog.Builder(this).setPositiveButton("OK", null).
//                            setMessage("ERROR: " + e).show();
//                }
//                return true;
//            }
//        }
//    }
}
