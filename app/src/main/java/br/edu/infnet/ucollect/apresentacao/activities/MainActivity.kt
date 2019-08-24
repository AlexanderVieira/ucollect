package br.edu.infnet.ucollect.apresentacao.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.viewmodel.EmpresaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var empresaViewModel: EmpresaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        empresaViewModel = ViewModelProviders
            .of(this)
            .get(EmpresaViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.app_bar_lista_id){

        }
        return super.onOptionsItemSelected(item)
    }
}
