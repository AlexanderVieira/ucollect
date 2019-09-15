package br.edu.infnet.ucollect.apresentacao.fragmentos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import br.edu.infnet.ucollect.R
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
        var currentUser = mAuth.currentUser

        activity?.let {
            if (currentUser != null) {
                var email = currentUser.email
                //Toast.makeText(it, email, Toast.LENGTH_LONG).show()
                perfil_nome_textView.setText(email)

            }
        }
    }


}
