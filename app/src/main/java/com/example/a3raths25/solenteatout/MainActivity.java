package com.example.a3raths25.solenteatout;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;

import java.io.IOException;
import java.util.ArrayList;

import static android.R.attr.name;
import static android.R.attr.rating;
import static com.example.a3raths25.solenteatout.R.id.et1;
import static com.example.a3raths25.solenteatout.R.id.et2;
import static com.example.a3raths25.solenteatout.R.id.et3;
import static com.example.a3raths25.solenteatout.R.id.et4;

public class MainActivity extends AppCompatActivity implements  LocationListener {
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
        }
        catch(SecurityException e) {

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();

                String name = extras.getString("name");
                String address= extras.getString("address");

                OverlayItem newResturant = new OverlayItem("name", "address", new GeoPoint(lat, lon));
                items.addItem(newResturant);
                mv.getOverlays().add(items);

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
}

