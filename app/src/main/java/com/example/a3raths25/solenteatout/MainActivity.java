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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  LocationListener {
    MapView mv;
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

        Items= new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(),null);
        OverlayItem name = new OverlayItem((EditText) findViewById(R.id.et1),(EditText) findViewById(R.id.et2),(EditText) findViewById(R.id.et3),(EditText) findViewById(R.id.et4), new GeoPoint())


    LocationManager mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

       Location location = locationManager.getLast
        if (location != null) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();


            // Get longitude of the current location

            String userId = "O6pGXcJy3C";
            //String username = "Nick";
            ParseObject globeobject = new ParseObject("global");
            globeobject.put("username","Alana");

            globeobject.put("userID",userId);

            final ParseGeoPoint point = new ParseGeoPoint(latitude, longitude);
            globeobject.put("Location", point);

        mv.getOverlays().add(items);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.AddNewResturant) {
            Intent intent = new Intent(this,AddNewResturantActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        if (item.getItemId() == R.id.preferences) {
            Intent intent = new Intent(this,PrefsActivity .class);
            startActivityForResult(intent, 1);
            return true;
        }

        if (item.getItemId() == R.id.LoadFromFile) {
            Intent intent = new Intent(this,LoadFromFileActivity .class);
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
    public void onLocationChanged(Location newLoc) {
        Toast.makeText
                (this, "Location=" +
                        newLoc.getLatitude() + " " +
                        newLoc.getLongitude(), Toast.LENGTH_LONG).show();
        mv.getController().setCenter(new GeoPoint(newLoc.getLatitude(), newLoc.getLongitude()));
    }
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Provider " + provider +
                " disabled", Toast.LENGTH_LONG).show();
    }
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Provider " + provider +
                " enabled", Toast.LENGTH_LONG).show();
    }
    public void onStatusChanged(String provider, int status, Bundle extras) {

        Toast.makeText(this, "Status changed: " + status,
                Toast.LENGTH_LONG).show();
    }
}

