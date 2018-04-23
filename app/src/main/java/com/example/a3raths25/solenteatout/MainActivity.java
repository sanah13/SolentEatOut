package com.example.a3raths25.solenteatout;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity implements LocationListener
{
    MapView mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationManager mgr=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

        setContentView(R.layout.activity_main);
        mv = (MapView)findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(16);
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

