package br.edu.infnet.ucollect.apresentacao.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Residuo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.residuo_card.*
import kotlinx.android.synthetic.main.residuo_card.view.*

class ResiduoDetalhesActivity : AppCompatActivity() {

    private lateinit var bancoDadosRef: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var residuo: Residuo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residuo_detalhes)

        mAuth = FirebaseAuth.getInstance()

        mAuth.currentUser?.let {
            currentUser = it
        }

        residuo = intent.getSerializableExtra("residuo") as Residuo

        layout_child_doado_card_view_id.imageView_card_doado.setImageResource(R.drawable.ic_battery_20_black_24dp)
        textView_card_doado_nome.text = residuo!!.nome
        textView_card_doado_descricao.text = residuo!!.descricao
        textView_card_residuo_doador.text = intent.getStringExtra("apelidoDoador")

        if(intent.getBooleanExtra("meusResiduos", false) || residuo.doadorId == currentUser.uid){

        }else if (!residuo.reservado){
            imagem_button_reserva_card.visibility = View.VISIBLE
        }

        setUpListener()
    }

    fun setUpListener(){
        imagem_button_reserva_card.setOnClickListener {
            bancoDadosRef.child("residuos")
                .child(residuo.residuoId)
                .child("reservado").setValue(true)

            var chave = bancoDadosRef.child("residuos-reservados").push().key

            bancoDadosRef.child("residuos-reservados").child(chave.toString()).setValue(mapOf(Pair("residuoID",residuo.residuoId),
                                                                                                        Pair("coletorId", currentUser.uid)))

        }
    }

    override fun onStart() {
        super.onStart()
        bancoDadosRef = FirebaseDatabase.getInstance().reference

    }
}