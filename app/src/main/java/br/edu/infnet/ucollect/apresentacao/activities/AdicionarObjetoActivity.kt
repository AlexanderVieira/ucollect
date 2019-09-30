package br.edu.infnet.ucollect.apresentacao.activities

import android.media.MediaPlayer
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Residuo
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_adicionar_objeto.*

class AdicionarObjetoActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var bancoDados: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_objeto)

        mAuth = FirebaseAuth.getInstance()
        bancoDados = FirebaseDatabase.getInstance().reference
        setUpListeners()
    }

    override fun onStart() {
        super.onStart()
        mAuth.currentUser?.let {
            currentUser = it
        }
    }

    private fun setUpListeners(){

        add_residuo_btn_salvar.setOnClickListener {botao ->

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

            val residuoChave = bancoDados.child("residuos").push().key

            val residuo = Residuo(residuoChave!!,
                    add_residuo_textInput_titulo.editText?.text.toString(),
                    add_residuo_textInput_descricao.editText?.text.toString(),
                    currentUser.uid,
                    2)

            val residuoValue = residuo.toMap()

            val updates = HashMap<String, Any>()
            updates["/residuos/$residuoChave"] = residuoValue
            updates["/usuarios-residuos/${currentUser.uid}/$residuoChave"] = residuoValue

            bancoDados.updateChildren(updates).addOnCompleteListener{tarefa ->
                    if(tarefa.isSuccessful){
                        Snackbar.make(botao,"Resíduo salvo com sucesso!",Snackbar.LENGTH_LONG).show()
                        add_residuo_textInput_descricao.editText?.text?.clear()
                        add_residuo_textInput_titulo.editText?.text?.clear()
                        add_residuo_textInput_titulo.requestFocus()
                    }
                }
        }

        add_residuo_btn_cancelar.setOnClickListener {
            onBackPressed()
        }


    }
}