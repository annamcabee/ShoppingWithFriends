package com.group15.shoppingwithfriends.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.Sale;
import com.group15.shoppingwithfriends.R;

import java.util.List;

public class DisplayMap extends Activity {

    /** Member variables **/
    GoogleMap m_googleMap;
    DataSource datasource;

    /**
     * on create launches map, and marks pins at valid points
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_map);
        createMapView();
        datasource = new DataSource(this);
        datasource.open();
        List<Sale> saleList = datasource.getAllSales();
        for (Sale s : saleList) {
            double latitude = s.getLat();
            double lon = s.getLongitude();
            LatLng pos = new LatLng(latitude, lon);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(pos);
            markerOptions.title(s.getItem() + " for $" + s.getPrice());
            m_googleMap.addMarker(markerOptions);
        }
    }

    /**
     * Initialises the mapview
     */
    private void createMapView() {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if(null == m_googleMap){
                m_googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == m_googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }


    /**
     * on create options
     * @param menu
     * @return true / false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * on options selection
     * @param item selected from menu
     * @return bool
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}