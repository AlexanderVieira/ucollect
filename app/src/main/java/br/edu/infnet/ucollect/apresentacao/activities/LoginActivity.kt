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
        var emailRecuperado = intent.getStringExtra(EmailFormActivity.EXTRA_EMAIL_USUARIO)
        edtxt_email_login.setText(emailRecuperado)
        setListeners()
    }

   /* override fun onStart() {
        super.onStart()
        var currentUser = mAuth.currentUser
        updateUI(currentUser)
    }*/

    private fun setListeners(){

        btn_login.setOnClickListener {
            var email = edtxt_email_login.text.toString()
            var senha = edtxt_senha_login.text.toString()
            Log.i(TAG, email)
            Log.i(TAG, senha)

            mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.i(TAG, "signInWithEmail:success")
                        val user = mAuth.currentUser
                        updateUI(user)
                    } else {
                        Log.i(TAG, "signInWithEmail:failure", task.exception)
                        //showToast(loginActivity, "Usuário e/ou senha inválidos!")
                        updateUI(null)
                    }
                }
            //
        }

        cadastrar_novo_button.setOnClickListener {
            var intent = Intent(this, EmailFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            showSnackbar(btn_login, "Olá " + currentUser.email!!)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{
            showSnackbar(btn_login,"Olá, cadastre-se ou insira suas credenciais.")
            /*startActivity(Intent(this, RegistroActivity::class.java))
            finish()*/
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