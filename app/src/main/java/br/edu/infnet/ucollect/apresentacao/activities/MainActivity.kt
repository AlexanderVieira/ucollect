package br.edu.infnet.ucollect.apresentacao.activities

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.fragmentos.*
import br.edu.infnet.ucollect.utils.LocationProviderUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var mAuth: FirebaseAuth
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    val CODE_REQUEST = 51
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        mAuth = FirebaseAuth.getInstance()
        //NetWorkTask(this).execute()

        self = this

        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient = LocationProviderUtil().getInstance(this)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    // task background
    /*class NetWorkTask(var act: MainActivity): AsyncTask<Void, Void, Void>(){

        var dialog =  Dialog(act, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)

        override fun onPreExecute() {

            val view = act.layoutInflater.inflate(R.layout.loading_screen, null)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            Thread.sleep(1000)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            dialog.dismiss()
        }
    }*/

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_perfil -> {
                //iniciarPerfilFragment()
                carregaFragment(PerfilFragment.newInstance())
            }
            R.id.nav_empresas -> {
                //iniciarEmpresasFragment()
                carregaFragment(EmpresasFragment.newInstance())
            }
            R.id.nav_residuos -> {
                //iniciarResiduosFragment()
                carregaFragment(ResiduosFragment.newInstance())
            }
            R.id.nav_maps -> {
                checkSelfPermission()
                carregaFragment(MapFragment.newInstance())
            }

            R.id.nav_logout -> {
                logout()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun carregaFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.menu_content, fragment).commit()
    }

    public fun detalhesEmpresa(dados: ArrayList<String>){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val detalhesEmpresaFragment = DetalhesEmpresaFragment.newInstance()
        val args = Bundle()
        args.putStringArrayList("dados", dados)
        detalhesEmpresaFragment.arguments = args
        fragmentTransaction.replace(R.id.menu_content,detalhesEmpresaFragment)
        fragmentTransaction.addToBackStack("anterior")
        fragmentTransaction.commit()
    }

    private fun logout(){
        var resultIntent = Intent(this, LoginActivity::class.java )
        startActivity(resultIntent)
        mAuth.signOut()
        finish()
    }

    private fun checkSelfPermission(){

        if (checkSelfPermission(baseContext, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)){

                Toast.makeText(this, "Aceita!", Toast.LENGTH_LONG).show()
            }
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), CODE_REQUEST)
        }
        else{

            //show_permission.text = getString(R.string.possuiPermissao)
            Log.i(TAG, "Possui permissão!")
            getUserLocation()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CODE_REQUEST){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //show_permission.text = getString(R.string.permissaoConcedida)
                Log.i(TAG, "Permissão concedida!")
                getUserLocation()
            }
            else{
                //show_permission.text = getString(R.string.permissaoNegada)
                Log.i(TAG, "Permissão negada!")
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation(){
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            //show_permission.text = "${it.latitude}; ${it.longitude}"
            Log.i(TAG, "Success Listener: ${it.latitude}; ${it.longitude}")
        }
    }

    companion object {
        lateinit var self: MainActivity
    }
}