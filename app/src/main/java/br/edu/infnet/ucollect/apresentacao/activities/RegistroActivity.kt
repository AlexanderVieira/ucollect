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
import kotlinx.android.synthetic.main.activity_registro.*

const val TAG: String = "Create"

class RegistroActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        mAuth = FirebaseAuth.getInstance()
        setListeners()
    }

    override fun onStart() {
        super.onStart()
        var currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    fun setListeners(){

        btn_cadastrar_registro.setOnClickListener {

            //var nome = edtxt_nome_registro.text.toString()
            var email = intent.getStringExtra("email")
            var senha = edtxt_senha_registro.text.toString()
            var confirmaSenha = edtxt_confirma_senha_registro.text.toString()

            if (senha == confirmaSenha){
                this@RegistroActivity.let { registroAcivity ->
                    mAuth.let { firebaseAuth ->
                        email?.let {email ->
                            Log.i(TAG, email)
                            Log.i(TAG, senha)
                            firebaseAuth.createUserWithEmailAndPassword(email, senha)
                                .addOnCompleteListener(registroAcivity) { task ->
                                    Log.i(TAG, task.toString())
                                    if (task.isSuccessful) {
                                        Log.i(TAG, "Autenticação realizada com sucesso!")
                                        //showToast(registroAcivity, "Cadastro realizado com sucesso!")
                                        val user = firebaseAuth.getCurrentUser()
                                        updateUI(user)
                                        var resultIntentLogin = Intent(registroAcivity, LoginActivity::class.java )
                                        startActivity(resultIntentLogin)
                                        //finish()
                                    } else {
                                        Log.i(TAG, "Autenticação falhou!", task.exception)
                                        //showToast(registroAcivity, "Usuário e/ou senha inválidos!")
                                        updateUI(null)
                                    }
                                }
                        }
                    }
                }
            }
            else
            {
                showToast(this@RegistroActivity, "Credenciais não conhecidem!")
            }
        }

        btn_cancelar_registro.setOnClickListener {
            finish()
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            /*var resultIntentLogin = Intent(this@RegistroActivity, LoginActivity::class.java )
            startActivity(resultIntentLogin)*/
            showSnackbar(btn_cadastrar_registro, "Olá " + currentUser.email!!)
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

    override fun onResume() {
        super.onResume()

    }

}
