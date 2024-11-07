package com.shalu.mapstravel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shalu.mapstravel.databinding.ActivityDetailWisataBinding

class DetailWisataActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var binding: ActivityDetailWisataBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var btnMaps : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailWisataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Terima data gambar dan nama
        val imageResId = intent.getIntExtra("Img", 0)
        val wisatades = intent.getStringExtra("des")
        val latitude = intent.getDoubleExtra("lat", 0.0)
        val longitude = intent.getDoubleExtra("lng", 0.0)

        // Set gambar dan nama wisata
        binding.imgWisata2.setImageResource(imageResId)
        binding.des.text = wisatades



        btnMaps = findViewById(R.id.btnMaps)
        btnMaps.setOnClickListener(){
            val intent = Intent(this,MapsActivity::class.java).apply{
                putExtra("lat", latitude)
                putExtra("lng", longitude)

            }
            startActivity(intent)

        }
        // Inisialisasi dan tampilkan lokasi di MapView
        mapView = binding.map
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        val location = LatLng(latitude, longitude)
        displayLocationOnMap(location)
    }


    private fun displayLocationOnMap(location: LatLng) {
        if (::googleMap.isInitialized) {
            googleMap.addMarker(MarkerOptions().position(location).title("Lokasi Wisata"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = true

        // Koordinat default (contoh), ini bisa diupdate saat koordinat aktual tersedia
        val latitude = intent.getDoubleExtra("lat", 0.0)
        val longitude = intent.getDoubleExtra("lng", 0.0)
        val location = LatLng(latitude, longitude)
        displayLocationOnMap(location)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}