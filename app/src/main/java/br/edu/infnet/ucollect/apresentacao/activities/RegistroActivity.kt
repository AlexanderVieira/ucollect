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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*

const val TAG: String = "RegistroActivity"

class RegistroActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    lateinit var database: FirebaseDatabase
    lateinit var usuariosRef: DatabaseReference
    lateinit var user: FirebaseUser
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        usuariosRef = database.getReference().child("usuarios")
        setListeners()
    }

    /*override fun onStart() {
        super.onStart()
        var currentUser = mAuth.currentUser
        updateUI(currentUser)
    }*/

    fun setListeners(){

        btn_cadastrar_registro.setOnClickListener {

            var nome = edtxt_nome_registro.text.toString()
            email = intent.getStringExtra(EmailFormActivity.EXTRA_EMAIL_USUARIO)
            var senha = edtxt_senha_registro.text.toString()
            var confirmaSenha = edtxt_confirma_senha_registro.text.toString()

            if (senha == confirmaSenha){
                    if(email != null){
                        mAuth.createUserWithEmailAndPassword(email, senha)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success")
                                    user = mAuth.currentUser!!

                                    if (user != null){

                                        var usuario = Usuario(
                                            user.uid,
                                            nome,
                                            "fulano",
                                            user.email.toString(),
                                            senha, confirmaSenha
                                        )
                                        usuariosRef.child(user.uid).setValue(usuario)
                                    }
                                    updateUI(user)

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.i(TAG, "createUserWithEmail:failure", task.exception)
                                    Toast.makeText(baseContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show()
                                    updateUI(null)
                                }
                                // ...
                            }
                    }
            }
            else
            {
                showToast(this@RegistroActivity, "Credenciais não conferem!")
            }
        }

        btn_cancelar_registro.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            showSnackbar(btn_cadastrar_registro, "Olá " + currentUser.email!!)
            startActivity(Intent(this, LoginActivity::class.java))
        }
        else{
            showSnackbar(btn_cadastrar_registro, "Olá, cadastre-se ou insira suas credenciais.")
        }
    }

    private fun showSnackbar(view: View, msg: String) {
        Snackbar.make( view, msg, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    private fun showToast(context: Context, msg:String){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}
