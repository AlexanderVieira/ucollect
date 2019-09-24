package br.edu.infnet.ucollect.apresentacao.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Residuo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_adicionar_objeto.*

class AdicionarObjetoActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var currentUser: FirebaseUser

    private lateinit var bancoDados: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_objeto)

        mAuth = FirebaseAuth.getInstance()

        bancoDados = FirebaseDatabase.getInstance()

        setUpListeners()
    }

    override fun onStart() {
        super.onStart()
        mAuth.currentUser?.let {
            currentUser = it
        }
    }


    private fun setUpListeners(){

        add_residuo_btn_salvar.setOnClickListener {

            if(TextUtils.isEmpty(add_residuo_textInput_titulo.editText?.text))
            {
                add_residuo_textInput_titulo.editText?.error = getString(R.string.campo_obridatorio)
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(add_residuo_textInput_descricao.editText?.text))
            {
                add_residuo_textInput_descricao.editText?.error = getString(R.string.campo_obridatorio)
                return@setOnClickListener
            }

            bancoDados.getReference("residuos").push().key?.let {

                var residuo = Residuo(it,
                    add_residuo_textInput_titulo.editText?.text.toString(),
                    add_residuo_textInput_descricao.editText?.text.toString(),
                    currentUser.uid,
                    2)

                bancoDados.getReference("Residuos").child(currentUser.uid).child(residuo.ResiduoId).setValue(residuo)

            }
        }

        add_residuo_btn_cancelar.setOnClickListener {
            onBackPressed()
        }


    }
}