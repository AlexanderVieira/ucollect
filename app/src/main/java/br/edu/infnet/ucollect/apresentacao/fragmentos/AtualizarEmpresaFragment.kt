package br.edu.infnet.ucollect.apresentacao.fragmentos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.infnet.ucollect.R
import kotlinx.android.synthetic.main.fragment_atualizar_empresa.*

class AtualizarEmpresaFragment : Fragment() {

    private var dados : ArrayList<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val arguments = arguments
        if (arguments != null) {
            //textViewQuestion.text = arguments.getString(STATEMENT_KEY)
            dados = arguments.getStringArrayList("dados")
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atualizar_empresa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dados?.let {
                edtxt_nome_atualizar_empresa_frg.setText(it.get(0))
                edtxt_email_atualizar_empresa_frg.setText(it.get(1))
                edtxt_tel_atualizar_empresa_frg.setText(it.get(2))
                edtxt_cnpj_atualizar_empresa_frg.setText(it.get(3))
                edtxt_end_atualizar_empresa_frg.setText(it.get(4))
            }
    }

    companion object {
        fun newInstance(): AtualizarEmpresaFragment{
            val atualizarEmpresaFragment = AtualizarEmpresaFragment()
            /*residuosFragment.arguments = Bundle().apply {
                // salva dados do parâmetro
            }*/
            return atualizarEmpresaFragment
        }
    }

}
