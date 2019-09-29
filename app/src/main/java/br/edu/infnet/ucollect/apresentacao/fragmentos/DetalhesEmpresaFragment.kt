package br.edu.infnet.ucollect.apresentacao.fragmentos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.infnet.ucollect.R
import kotlinx.android.synthetic.main.fragment_detalhes_empresa.*


class DetalhesEmpresaFragment : Fragment() {

    private var dados: ArrayList<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalhes_empresa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            dados = it.getStringArrayList("dados")
            textView2.text = dados?.get(0)
            textView3.text = dados?.get(1)
            textView4.text = dados?.get(2)
        }
        setListeners()
    }

    private fun setListeners(){
        btn_atualizar_empresa.setOnClickListener {

            val args = Bundle()
            args.putStringArrayList("dados", dados)
            var atualizarEmpresasFragment = AtualizarEmpresaFragment.newInstance()
            atualizarEmpresasFragment.arguments = args

            carregaFragment(atualizarEmpresasFragment)
        }

        btn_excluir_empresa.setOnClickListener {

            carregaFragment(AtualizarEmpresaFragment.newInstance())
        }
    }

    private fun carregaFragment(fragment: Fragment){
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(R.id.menu_content, fragment).commit()
        }
    }

    companion object {
        fun newInstance(): DetalhesEmpresaFragment{
            val detalhesEmpresaFragment = DetalhesEmpresaFragment()
            /*residuosFragment.arguments = Bundle().apply {
                // salva dados do parâmetro
            }*/
            return detalhesEmpresaFragment
        }
    }
}