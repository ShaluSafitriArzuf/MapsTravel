package com.shalu.mapstravel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shalu.mapstravel.databinding.ActivityMaps2Binding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMaps2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaps2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SupportMapFragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Periksa apakah data koordinat tersedia
        val latitude = intent.getDoubleExtra("lat", 0.0)
        val longitude = intent.getDoubleExtra("lng", 0.0)
        if (latitude == 0.0 && longitude == 0.0) {
            Log.e("MapsActivity", "Koordinat tidak tersedia atau 0.0")
        } else {
            Log.d("MapsActivity", "Koordinat tersedia: lat=$latitude, lng=$longitude")
        }

        val lokasiWisata = LatLng(latitude, longitude)
        mMap.addMarker(
            MarkerOptions()
                .position(lokasiWisata)
                .title("Lokasi Wisata")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasiWisata, 15f))
        mMap.isTrafficEnabled = true
    }

}