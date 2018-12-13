package project.capstone.sw.yahaja;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MatchTap extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    String u_id;
    double lat = 37.284;
    double lon = 127.044;

    public MatchTap(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_match, container, false);

        StoredUserSession storedUserSession = new StoredUserSession(getContext());
        u_id = storedUserSession.getUserSession();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);

        Button btn_win = view.findViewById(R.id.buttonWin);
        Button btn_lose = view.findViewById(R.id.buttonLose);

        btn_win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //query
                RequestHttp requestHttp = new RequestHttp();

                StoredUserSession storedUserSession = new StoredUserSession(getActivity().getApplicationContext());
                String u_id = storedUserSession.getUserSession();

                System.out.println(u_id);

                String matchWinRequest = "http://13.59.95.38:3000/match_result_win?account_id=" + u_id;

                requestHttp.requestGet(matchWinRequest);

                Toast.makeText(getActivity(), "결과 입력이 완료되었습니다!", Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent(getActivity(), MainActivity.class);
                resultIntent.putExtra("matchFragment", "resultMatch");
                startActivity(resultIntent);


            }
        });

        btn_lose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RequestHttp requestHttp = new RequestHttp();

                StoredUserSession storedUserSession = new StoredUserSession(getActivity().getApplicationContext());
                String u_id = storedUserSession.getUserSession();

                System.out.println(u_id);

                String matchLoseRequest = "http://13.59.95.38:3000/match_result_lose?account_id=" + u_id;
                String matchRequest = "http://13.59.95.38:3000/match_result?account_id=" + u_id;

                requestHttp.requestGet(matchLoseRequest);
                requestHttp.requestGet(matchRequest);

                Toast.makeText(getActivity(), "결과 입력이 완료되었습니다!", Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent(getActivity(), MainActivity.class);
                resultIntent.putExtra("matchFragment", "resultMatch");
                startActivity(resultIntent);

            }
        });

        return view;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public void onMapReady(GoogleMap googleMap){

        LatLng currentGPS = new LatLng(lat, lon);
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        if(checkPermission()) map.setMyLocationEnabled(true);

        map.addMarker(new MarkerOptions().position(currentGPS)).setVisible(true);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentGPS, 15);
        map.moveCamera(cameraUpdate);

        String address = getCurrentAddress(currentGPS);
        TextView addressView = getActivity().findViewById(R.id.addressView);
        addressView.setText("주소 : " + address);

    }

    @Override
    public void onStart() {
        super.onStart();

        if (checkPermission()) {
            if (map!=null)
                map.setMyLocationEnabled(true);
        }

    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private boolean checkPermission() {

        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {
            return true;
        }

        return false;

    }

    public String getCurrentAddress(LatLng latlng) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

        List<Address> addresses ;

        try {
            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);

            Address address = addresses.get(0);

            return address.getAddressLine(0).toString();
        } catch (IOException ioException) {
            Log.d("Geocoder", "ERROR");
        }

        return "Failed to find address.";
    }

    public void onButtonWinClickedListener(){

    }

}
