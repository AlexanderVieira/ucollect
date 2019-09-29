package br.edu.infnet.ucollect.apresentacao.fragmentos


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.activities.AdicionarObjetoActivity
import br.edu.infnet.ucollect.apresentacao.activities.MinhasDoacoesActivity
//import br.edu.infnet.ucollect.apresentacao.activities.MinhasDoacoesActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.nav_header_main.*


class PerfilFragment : Fragment() {

    var mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpListeners()

        var currentUser = mAuth.currentUser

        activity?.let {
            if (currentUser != null) {
                var email = currentUser.email
                //Toast.makeText(it, email, Toast.LENGTH_LONG).show()
                perfil_nome_textView.setText(email)

            }
        }
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
