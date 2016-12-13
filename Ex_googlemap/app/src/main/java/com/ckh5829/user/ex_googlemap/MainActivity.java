package com.ckh5829.user.ex_googlemap;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    //위도,경도
    double latitude, longitude;
    //위치정보 확인 및 관리
    LocationManager l_manager;
    //위치정보 객체
    Location location;

    //위도+경도 복합 저장클래스
    LatLng MY_POSITION = null;
    //지도상에 마크표시
    MarkerOptions marker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위치정보 객체 생성
        l_manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //GPS 정보 우선
        //location = l_manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        location = l_manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


        //location객체를 통해 위도와 경도를 얻기
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        Log.d("MY", "latitude: "+latitude);
        Log.d("MY", "longitude: "+longitude);

        //위도+경도
        MY_POSITION = new LatLng(latitude, longitude);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        //onMapReady호출 (implements OnMapReadyCallback)
        mapFragment.getMapAsync(this);

    }

    //OnMapReadyCallback 인터페이스 구현
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //맵초기화 메서드
        //현재위치로 이동
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(MY_POSITION));
        //축척 비율 (1~23까지 실수값으로 존재, 숫자가 커질수록 확대범위가 넓다.
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);
        //화면확대(이동)
        googleMap.animateCamera(zoom);
        //maker표시
        marker = new MarkerOptions();
        marker.position(MY_POSITION);
        marker.title("마실: "+getAddress(latitude, longitude));


        googleMap.addMarker(marker).showInfoWindow();
    }

    //위도와 경도기반으로 주소반환 메서드
    public String getAddress(double lat, double lng) {

        String address = "";

        //위치정보 활용을 위한 구글 API 객체
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        //주소를 목록화

        List<Address>  list = null;
        //위도,경도,조회갯수
        try {
            list = geocoder.getFromLocation(lat, lng, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (list == null) {
            Toast.makeText(this, "해당 주소가 없습니다.", Toast.LENGTH_SHORT).show();
            return "";
        } else {
            Address addr = list.get(0);
            address = addr.getCountryName() + " "       //나라
                    + addr.getAdminArea() + " "         //시
                    + addr.getLocality() + " "          //군
                    + addr.getSubThoroughfare() + " "   //동
                    + addr.getFeatureName();            //번지

            return address;
        }
    }

}











