package br.edu.infnet.ucollect.apresentacao.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.edu.infnet.ucollect.R
import kotlinx.android.synthetic.main.activity_email_form.*

class EmailFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_form)

        setListeners()
    }

    fun setListeners(){
        btn_continuar_email_form.setOnClickListener {

            var email = edtxt_email_form.text.toString()
            if (!email.isNullOrEmpty()){

                // cria nova intent
                var resultIntentRegistro = Intent(this@EmailFormActivity, RegistroActivity::class.java )

                // passa para a intent um dado chamado de email
                resultIntentRegistro.putExtra("email", email)

                // inicia a transição
                startActivity(resultIntentRegistro)
                //finish()
            } else{
                Toast.makeText(this, "Email é Obrigatório!", Toast.LENGTH_LONG).show()
            }
        }

        btn_registrado_email_form.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
