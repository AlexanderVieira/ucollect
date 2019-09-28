package br.edu.infnet.ucollect.apresentacao.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_email_form.*
import kotlinx.android.synthetic.main.activity_login.*


class EmailFormActivity : AppCompatActivity() {

   /* private lateinit var database: FirebaseDatabase
    private lateinit var usuarioReference: DatabaseReference*/
    private var usuario: Usuario? = null
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_form)

        btn_registrado_email_form.requestFocus()

        setListeners()
    }

    fun setListeners(){
        btn_continuar_email_form.setOnClickListener {

            var database = FirebaseDatabase.getInstance()
            var usuarioReference = database.getReference().child("usuarios")

            email = edtxt_email_form.text.toString()
            if (!email.isNullOrEmpty()){

                val usuarioListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        for (contatoSnapshot in dataSnapshot.children) {

                            var user = contatoSnapshot.getValue(Usuario::class.java)

                            if(user != null){

                                if(user?.email == email){

                                    usuario = user
                                }
                            }
                        }

                        updateUI(usuario)

                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                        Log.i(TAG, "load:onCancelled", databaseError.toException())
                        Toast.makeText(baseContext, "Failed to load post.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
                usuarioReference.addValueEventListener(usuarioListener)

            } else {
                Toast.makeText(this, "Email é Obrigatório!", Toast.LENGTH_LONG).show()
            }

        }

        btn_registrado_email_form.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*btn_cancelar_email_form.setOnClickListener {
            finish()
        }*/
    }

    private fun updateUI(currentUser: Usuario?) {
        if (currentUser != null){
            showSnackbar(btn_continuar_email_form, "Olá " + currentUser.email)
            startActivity(Intent(this, LoginActivity::class.java).putExtra(EXTRA_EMAIL_USUARIO,  currentUser.email))
            finish()
        }
        else{
            showSnackbar(btn_continuar_email_form,"Olá, cadastre-se ou insira suas credenciais.")
            startActivity(Intent(this, RegistroActivity::class.java).putExtra(EXTRA_EMAIL_USUARIO,  email))
            finish()
        }
    }

    private fun showSnackbar(view: View, msg: String) {
        Snackbar.make( view, msg, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    private fun showToast(context: Context, msg:String){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    companion object {

        private const val TAG = "EmailFormActivity"
        const val EXTRA_EMAIL_USUARIO = "emailUsuario"
        const val EXTRA_CAMPO_EMAIL = "campoEmail"
    }
}
