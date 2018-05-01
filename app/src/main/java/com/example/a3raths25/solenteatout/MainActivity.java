package com.example.a3raths25.solenteatout;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.example.a3raths25.solenteatout.R.id.et1;
import static com.example.a3raths25.solenteatout.R.id.et2;
import static com.example.a3raths25.solenteatout.R.id.et3;
import static com.example.a3raths25.solenteatout.R.id.et4;

public class MainActivity extends AppCompatActivity implements LocationListener {
    MapView mv;
    double lat = 51.05;
    double lon = -1.40435;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        setContentView(R.layout.activity_main);
        mv = (MapView) findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(16);
        mv.getController().setCenter(new GeoPoint(51.05, -1.404351));

        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), markerGestureListener);
        LocationManager mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
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

                OverlayItem newResturant = new OverlayItem(name, address, new GeoPoint(lat, lon));
                items.addItem(newResturant);
                mv.invalidate(); // redraw the map so that the marker is shown immediately

                mv.getOverlays().add(items);
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/NewResturants.txt"), true);
                    pw.write(name + ",");
                    pw.write(address + ",");
                    pw.write(cuisine + ",");
                    pw.write(rating);
                    pw.close();
                } catch (IOException e) {
                    new AlertDialog.Builder(this).setPositiveButton("OK", null).
                            setMessage("ERROR: " + e).show();
                }

            }
        }
    }

    public void onLocationChanged(Location newLoc) {
        Toast.makeText
                (this, "Location=" +
                        newLoc.getLatitude() + " " +
                        newLoc.getLongitude(), Toast.LENGTH_LONG).show();
        mv.getController().setCenter(new GeoPoint(newLoc.getLatitude(), newLoc.getLongitude()));
        lat = newLoc.getLatitude();
        lon = newLoc.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    public void onClick(View view) {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
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
        if (item.getItemId() == R.id.SaveAllAddedResturantsToFile) {
            //if (items.size() > 0) {

                try {
                    PrintWriter pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/NewResturants.csv"), true);
                    for (int i = 0; i < items.size(); i++) {
                        OverlayItem oitem = items.getItem(i);
                        System.out.println(oitem.getTitle() + oitem.getSnippet() + oitem.getPoint());
                        pw.write(oitem.getTitle() + "," + oitem.getSnippet() + "," + oitem.getPoint() + "," + "\n");
                    }
                    pw.close();
                }
                catch (IOException e) {
                    new AlertDialog.Builder(this).setPositiveButton("OK", null).
                            setMessage("ERROR: " + e).show();

            try {
                PrintWriter pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/NewResturants.txt"));
                for (int i = 0; i < items.size(); i++) {
                    OverlayItem oitem = items.getItem(i);
                    System.out.println(oitem.getTitle() + oitem.getSnippet() + oitem.getPoint());
                    pw.write(oitem.getTitle() + "," + oitem.getSnippet() + "," + oitem.getPoint() + "\n");

                }
                pw.close();
            } catch (IOException e) {
                new AlertDialog.Builder(this).setPositiveButton("OK", null).
                        setMessage("ERROR: " + e).show();
            }

//            PrintWriter pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/NewResturants.csv");
            //newResturant.getTitle() - gives the title
            // newResturant.getSnippet() - gives the description
            //items.getItem(index) - gives the item at position 'index' within the overlay
            // items.size() - number of OverlayItems on the overlay.
            return true;
        }
        if (item.getItemId() == R.id.preferences) {
            Intent intent = new Intent(this, PrefsActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }
        if (item.getItemId() == R.id.LoadFromFile) {
            BufferedReader reader = null;
            String line;
            try {
                String filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/NewResturants.txt";
                File file = new File(filepath);
                reader = new BufferedReader(new FileReader(filepath));
                while ((line = reader.readLine()) != null) {
                    String[] components = line.split(",");
                    if (components.length >= 4) {
                        try {

                            double lon = Double.parseDouble(components[3]);
                            double lat = Double.parseDouble(components[2]);

                            OverlayItem oitem = new OverlayItem(components[0], components[1], new GeoPoint(lat, lon));
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + oitem);
                            items.addItem(oitem);
                        } catch (NumberFormatException e) {
                            System.out.println("error parsing file" + e);
                        }
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mv.getOverlays().add(items);
            return true;

//                if (item.getItemId() == R.id.LoadFromWeb) {
//
//                    return true;
//                }
        }

        return false;
    }

    public void onDestroy() {
        super.onDestroy();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.commit();
    }
}