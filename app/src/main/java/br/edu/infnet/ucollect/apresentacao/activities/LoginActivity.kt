package br.edu.infnet.ucollect.apresentacao.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.infnet.ucollect.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        setListeners()
        //dsdsdsds
    }

    override fun onStart() {
        super.onStart()
        var currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun setListeners() {

        // cria um listener que dispara a função ao ser clicado
        cadastrar_novo_button.setOnClickListener {

            // cria uma variável do tipo Intent com o contexto e a classe da nova activity
            var intent = Intent(this, EmailFormActivity::class.java)

            // chama o método para fazer a transição para a outra activity
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            var email = edtxt_email_login.text.toString()
            var senha = edtxt_senha_login.text.toString()
            Log.i(TAG, email)
            Log.i(TAG, senha)

            this@LoginActivity.let { loginActivity ->
                mAuth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(loginActivity) { task ->
                        if (task.isSuccessful) {
                            Log.i(TAG, "signInWithEmail:success")
                            val user = mAuth.currentUser
                            updateUI(user)
                            var resultIntent = Intent(loginActivity, MainActivity::class.java)
                            startActivity(resultIntent)
                            //finish()
                        } else {
                            Log.i(TAG, "signInWithEmail:failure", task.exception)
                            //showToast(loginActivity, "Usuário e/ou senha inválidos!")
                            updateUI(null)
                        }
                    }
            }
        }

    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            /*var resultIntent = Intent(this@LoginActivity, MainActivity::class.java )
            startActivity(resultIntent)*/
            showSnackbar(btn_login, "Olá " + currentUser.email!!)
        } else {
            showSnackbar(btn_login, "Olá, cadastre-se ou insira suas credenciais.")
        }
    }

    private fun showSnackbar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    private fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()

    }
}