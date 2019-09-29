package br.edu.infnet.ucollect.apresentacao.fragmentos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.infnet.ucollect.R

class AtualizarEmpresaFragment : Fragment() {

    private var dados : ArrayList<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atualizar_empresa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*arguments?.let{
            dados = it.getStringArrayList("dados")
            edtxt_nome_atualizar_empresa_frg.text = dados?.get(0) as Editable
            edtxt_email_atualizar_empresa_frg.text = dados?.get(1) as Editable
            edtxt_tel_atualizar_empresa_frg.text = dados?.get(2) as Editable
        }*/
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
