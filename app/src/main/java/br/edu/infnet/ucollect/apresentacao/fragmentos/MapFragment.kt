package br.edu.infnet.ucollect.apresentacao.fragmentos

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.facebook.FacebookSdk.getApplicationContext
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMapClickListener {

    val TAG = "MapFragment"
    val NIVEL_ZOOM = 12.0f
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        var rootView =  inflater.inflate(br.edu.infnet.ucollect.R.layout.fragment_map, container, false)

        // Obtêm o SupportMapFragment
        mapFragment = childFragmentManager.findFragmentById(br.edu.infnet.ucollect.R.id.frg_google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return rootView
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {

        locationManager = getApplicationContext().getSystemService(LOCATION_SERVICE) as LocationManager
        var provider = locationManager.getBestProvider(Criteria(), true)
        Log.i(TAG, provider!!)
        mMap = googleMap!!
        mMap.clear()
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL)
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.isMyLocationEnabled = true
        mMap.setOnMapClickListener(this)

        // Adiciona Marcador e move a câmera
        var associacaoRenovar = LatLng(-22.8055764, -43.380620199999996)
        mMap.addMarker(MarkerOptions().position(associacaoRenovar).title("Associação Renovar").snippet("Rua Professor Bernardino Rocha 15, Pavuna"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(associacaoRenovar,NIVEL_ZOOM))

        var coopQuitungo = LatLng(-22.8334213, -43.2900184)
        mMap.addMarker(MarkerOptions().position(coopQuitungo).title("COOP QUITUNGO").snippet("Rua Suruí, 1109, Galpão 01, Brás de Pina"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coopQuitungo,NIVEL_ZOOM))

        var barraCoop = LatLng(-22.8459628, -43.3243175)
        mMap.addMarker(MarkerOptions().position(barraCoop).title("Barra Coop - Irajá").snippet("Av. Bom Senhor Félix, 512, Irajá"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barraCoop,NIVEL_ZOOM))

        var CoopFuturo = LatLng(-22.8334213, -43.2977184)
        mMap.addMarker(MarkerOptions().position(CoopFuturo).title("CoopFuturo").snippet("Avenida Monsenhor Félix 512, Irajá"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CoopFuturo,NIVEL_ZOOM))

        var pevLeroyMerlin = LatLng(-22.8754215, -43.268206)
        mMap.addMarker(MarkerOptions().position(pevLeroyMerlin).title("PEV Leroy Merlin Del Castilho").snippet("Rua das projetadas, 550, Del Castilho"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pevLeroyMerlin,NIVEL_ZOOM))

        var extraLojaReciclagem = LatLng(-22.8334213, -43.2855184)
        mMap.addMarker(MarkerOptions().position(extraLojaReciclagem).title("Extra - Loja com Estação de Reciclagem").snippet("Rua Suruí, 1109, Galpão 01, Brás de Pina"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(extraLojaReciclagem, NIVEL_ZOOM))
    }

    override fun onMapClick(latLng: LatLng?) {
        Toast.makeText(getApplicationContext(),"${latLng!!.latitude}; ${latLng.longitude}", Toast.LENGTH_LONG ).show()
    }

    companion object {
        var mapFragment : SupportMapFragment?=null
        val TAG: String = MapFragment::class.java.simpleName
        fun newInstance() = MapFragment()
    }

}
