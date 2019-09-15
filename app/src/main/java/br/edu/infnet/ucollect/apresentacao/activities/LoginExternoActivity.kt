package br.edu.infnet.ucollect.apresentacao.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.infnet.ucollect.R
import kotlinx.android.synthetic.main.activity_email_form.*
import kotlinx.android.synthetic.main.activity_login_externo.*

class LoginExternoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_externo)

        setListeners()
    }

    fun setListeners(){
        tv_email_login_externo.setOnClickListener {
            var resultIntentEmail = Intent(this@LoginExternoActivity, EmailFormActivity::class.java )
            startActivity(resultIntentEmail)
            finish()
        }
    }

}
