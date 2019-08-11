package br.edu.infnet.ucollect.apresentacao.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}
