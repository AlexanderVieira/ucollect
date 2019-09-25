package br.edu.infnet.ucollect.apresentacao.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.edu.infnet.ucollect.R
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_email_form.*
import kotlinx.android.synthetic.main.activity_login_externo.*

class LoginExternoActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    lateinit var user: FirebaseUser
    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_externo)

        callbackManager = CallbackManager.Factory.create()
        login_button.setReadPermissions("email", "public_profile")

        setListeners()
    }

    fun setListeners(){

        tv_email_login_externo.setOnClickListener {
            var resultIntentEmail = Intent(this@LoginExternoActivity, EmailFormActivity::class.java )
            startActivity(resultIntentEmail)
            finish()
        }

        login_button.setOnClickListener{
            login_button.registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    handleFacebookAccessToken(result?.accessToken)
                }

                override fun onCancel() {
                    showToast(this@LoginExternoActivity, "Usuário cancelou!")
                }

                override fun onError(error: FacebookException?) {
                    showToast(this@LoginExternoActivity, error!!.message.toString())
                }
            })
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {

        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    user = mAuth.currentUser!!
                    updateUI(user)

                } else {

                    updateUI(null)
                    //showToast(baseContext, "Autenticação com o Facebook falhou!")
                    /*Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()*/
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            showSnackbar(login_button, "Olá " + currentUser.email!!)
            startActivity(Intent(this, MainActivity::class.java))
        }
        else{
            showSnackbar(login_button,"Autenticação com o Facebook falhou!")
            //startActivity(Intent(this, MainActivity::class.java))
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

}
