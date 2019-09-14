package br.edu.infnet.ucollect.apresentacao.activities

import android.app.Dialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import androidx.fragment.app.FragmentTransaction
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.fragmentos.DetalhesEmpresaFragment
import br.edu.infnet.ucollect.apresentacao.fragmentos.EmpresasFragment
import br.edu.infnet.ucollect.apresentacao.fragmentos.ResiduosFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        NetWorkTask(this).execute()

        self = this

        /*val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
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
    class NetWorkTask(var act: MainActivity): AsyncTask<Void, Void, Void>(){

        var dialog =  Dialog(act, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)

        override fun onPreExecute() {

            val view = act.layoutInflater.inflate(R.layout.loading_screen, null)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            Thread.sleep(3000)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            dialog.dismiss()
        }
    }

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                iniciarEmpresasFragment()
            }
            R.id.nav_gallery -> {
                iniciarResiduosFragment()
            }

            R.id.nav_logout -> {
                logout()
            }

            R.id.nav_share -> {

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun iniciarEmpresasFragment(){
        val empresasFragment = EmpresasFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.menu_content, empresasFragment).commit()
    }

    private fun iniciarResiduosFragment(){
        val empresasFragment = ResiduosFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.menu_content, empresasFragment).commit()
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
        finish()
    }

    companion object {
        lateinit var self: MainActivity
    }


}


