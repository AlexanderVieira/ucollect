package br.edu.infnet.ucollect.apresentacao.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Usuario
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_email_form.*


class EmailFormActivity : AppCompatActivity() {

    private lateinit var usuarioReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_form)

        usuarioReference = FirebaseDatabase.getInstance().reference
            .child("usuarios").child("001")
        setListeners()
    }

    fun setListeners(){
        btn_continuar_email_form.setOnClickListener {

            var email = edtxt_email_form.text.toString()
            if (!email.isNullOrEmpty()){

                val usuarioListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val usuario = dataSnapshot.getValue(Usuario::class.java)
                        // [START_EXCLUDE]
                        usuario?.let {
                            var nome = it.nome
                            var apelido = it.apelido
                            var email = it.email
                        }
                        // [END_EXCLUDE]
                        if(usuario?.email == email){
                            var resultIntentLogin = Intent(baseContext, LoginActivity::class.java )
                            resultIntentLogin.putExtra(EXTRA_EMAIL_USUARIO, usuario.email)
                            startActivity(resultIntentLogin)
                            finish()

                        }else{
                            var resultIntentRegistro = Intent(baseContext, RegistroActivity::class.java )
                            resultIntentRegistro.putExtra(EXTRA_CAMPO_EMAIL, email)
                            startActivity(resultIntentRegistro)
                            finish()
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                        // [START_EXCLUDE]
                        Toast.makeText(baseContext, "Failed to load post.",
                            Toast.LENGTH_SHORT).show()
                        // [END_EXCLUDE]
                    }
                }
                usuarioReference.addValueEventListener(usuarioListener)

            }else{
                Toast.makeText(this, "Email é Obrigatório!", Toast.LENGTH_LONG).show()
            }

        }

        btn_cancelar_email_form.setOnClickListener {
            finish()
        }
    }

    companion object {

        private const val TAG = "EmailFormActivity"
        const val EXTRA_EMAIL_USUARIO = "emailUsuario"
        const val EXTRA_CAMPO_EMAIL = "campoEmail"
    }
}
