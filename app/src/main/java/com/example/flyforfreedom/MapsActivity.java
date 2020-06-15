package com.example.flyforfreedom;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText startingPoint, endingPoint;
    Button button;
    private static final String TAG = "MyActivity";
    LatLngBounds.Builder builder;
    CameraUpdate cu;
    Marker startMarker = null;
    Marker endMarker = null;
    LatLng startPosition;
    LatLng endPosition;
    float[] result = new float[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        startingPoint = (EditText)findViewById(R.id.startingPoint);
        endingPoint = (EditText)findViewById(R.id.endingPoint);
        button = (Button)findViewById(R.id.button);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        BitmapDescriptor descriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        final MarkerOptions startLocation = new MarkerOptions().position(sydney);
        final MarkerOptions endLocation = new MarkerOptions().position(sydney);
        final List<Marker> markersList = new ArrayList<Marker>();
        startMarker = mMap.addMarker(startLocation);
        endMarker = mMap.addMarker(endLocation);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));


        startingPoint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchString = startingPoint.getText().toString();
                Geocoder geocoder = new Geocoder(MapsActivity.this);
                List<Address> addressList = new ArrayList<>();
                try {
                    addressList = geocoder.getFromLocationName(searchString, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(addressList.size() > 0) {
                    Address address = addressList.get(0);
                    Log.d(TAG, "geoLocate: found a location" + address.getLatitude() + address.getLongitude());
                    startPosition = new LatLng(address.getLatitude(), address.getLongitude());
                    startMarker.setPosition(startPosition);
                    startMarker.setTitle(address.getCountryName());
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(startPosition));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(5));
                    markersList.add(startMarker);
                }
            }
        });

        endingPoint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchString = endingPoint.getText().toString();
                Geocoder geocoder = new Geocoder(MapsActivity.this);
                List<Address> addressList = new ArrayList<>();
                try {
                    addressList = geocoder.getFromLocationName(searchString, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(addressList.size() > 0) {
                    Address address = addressList.get(0);
                    Log.d(TAG, "geoLocate: found a location" + address.getLatitude() + address.getLongitude());
                    endPosition = new LatLng(address.getLatitude(), address.getLongitude());
                    endMarker.setPosition(endPosition);
                    endMarker.setTitle(address.getCountryName());
                    markersList.add(endMarker);
                    builder = new LatLngBounds.Builder();
                    for(Marker m:markersList) {
                        builder.include(m.getPosition());
                    }
                    int padding = 100;
                    LatLngBounds bounds = builder.build();
                    cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                    mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback(){
                        @Override
                        public void onMapLoaded() {
                            mMap.animateCamera(cu);
                        }
                    });
                }
            }

        });
    }

    public void startFly(View view) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(startPosition));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(2));

        final CameraPosition endCameraPosition = new CameraPosition.Builder().target(endPosition).zoom(8).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(endCameraPosition), 3000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                Log.d("Action", "Animation finished end");
            }

            @Override

            public void onCancel() {
                Log.d("Action", "Animation interruted");
            }
        });
        Location.distanceBetween(startMarker.getPosition().latitude, startMarker.getPosition().longitude,endMarker.getPosition().latitude, endMarker.getPosition().longitude, result);
//        Toast.makeText(getApplicationContext(), " Toatal Distance : " + result[0]/1000, Toast.LENGTH_SHORT).show();
        String name = getIntent().getStringExtra("name");
        String speed = getIntent().getStringExtra("speed");
        Intent intent = new Intent();
        intent.setClass(MapsActivity.this,ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("speed",speed);
        bundle.putString("distanceResult", String.valueOf(result[0]/1000));
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
