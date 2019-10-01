package br.edu.infnet.ucollect.apresentacao.fragmentos


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.activities.AdicionarObjetoActivity
import br.edu.infnet.ucollect.apresentacao.activities.MinhasDoacoesActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_perfil.*


class PerfilFragment : Fragment() {

    var mAuth = FirebaseAuth.getInstance()
    private lateinit var bancoDadosRef: DatabaseReference
    lateinit var myListener: ValueEventListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()

        var currentUser = mAuth.currentUser

        bancoDadosRef = FirebaseDatabase.getInstance().reference.child("usuarios-residuos").child(currentUser!!.uid)

        activity?.let {
            if (currentUser != null) {
                var email = currentUser.email
                perfil_nome_textView.setText(email)
            }


        }

        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                // nada
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()){
                    var contador = dataSnapshot.childrenCount

                    if (perfil_itens_doados_textView != null){
                        perfil_itens_doados_textView.text = contador.toString()
                    }
                } else {
                    perfil_itens_doados_textView.text = "0"
                }
            }
        }
        myListener = bancoDadosRef.addValueEventListener(listener)

    }

    private fun setUpListeners(){
        perfil_adicionar_button.setOnClickListener {
            activity?.let {
                var intent = Intent(it, AdicionarObjetoActivity::class.java)
                startActivity(intent)
            }
        }

        perfil_ver_items_button.setOnClickListener {
            activity?.let {
                var intent = Intent(it, MinhasDoacoesActivity::class.java)
                startActivity(intent)
            }
        }
    }

    companion object {
        fun newInstance(): PerfilFragment{
            val perfilFragment = PerfilFragment()
            return perfilFragment
        }
    }
}
