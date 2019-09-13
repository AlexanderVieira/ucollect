package br.edu.infnet.ucollect.apresentacao.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Usuario
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_email_form.*


class EmailFormActivity : AppCompatActivity() {

    //private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_form)

        setListeners()
    }

    fun setListeners(){
        btn_continuar_email_form.setOnClickListener {

            var email = edtxt_email_form.text.toString()
            if (!email.isNullOrEmpty()){

                val database = FirebaseDatabase.getInstance()
                val contatoRef = database.getReference("usuarios")

                /*var database = FirebaseDatabase.getInstance().reference
                var contatoRef = database.child("usuarios")*/

                contatoRef.addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var value = dataSnapshot.getValue().toString()
                        Log.i("FIREBASE", "Value is: " + value!!)

                        for (contatoSnapshot in dataSnapshot.children) {

                            var contato = contatoSnapshot.getValue(Usuario::class.java)
                            //myContacts.add(contato.toString())
                        }

                        /*val snapshotIterator = dataSnapshot.children
                        val iterator = snapshotIterator.iterator()

                        myContacts.clear()

                        while (iterator.hasNext()) {
                            val next = iterator.next() as DataSnapshot

                            val match = next.getValue(Pessoa::class.java)
                            val key = next.key
                            //listKey.add(key!!)
                            myContacts.add(match.toString())
                        }*/
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w("FIREBASE", "Failed to read value.", error.toException())
                    }
                })

                var resultIntentRegistro = Intent(this@EmailFormActivity, RegistroActivity::class.java )
                resultIntentRegistro.putExtra("email", email)
                startActivity(resultIntentRegistro)
                //finish()
            }else{
                Toast.makeText(this, "Email é Obrigatório!", Toast.LENGTH_LONG).show()
            }

        }

        btn_cancelar_email_form.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

    }
}
