package br.edu.infnet.ucollect.apresentacao.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.infnet.ucollect.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_detalhes_empresa.*


class DetalhesEmpresaFragment : Fragment() {

    private var dados: ArrayList<String>? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_list -> {

                carregarFragment(EmpresasFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_save -> {

                //atualizaFragmentNewInstance(dados, "salvar", 'S')
                carregarFragment(AtualizarEmpresaFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_update -> {

                atualizarFragmentNewInstance(dados, "atualizar", 'A')
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_delete -> {

                atualizarFragmentNewInstance(dados, "excluir", 'D')
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_localization -> {

                carregarFragment(MapFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         var rootView = inflater.inflate(R.layout.fragment_detalhes_empresa, container, false)

        val navView: BottomNavigationView = rootView.findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            dados = it.getStringArrayList("dados")
            textView2.text = dados?.get(0)
            textView3.text = dados?.get(1)
            textView4.text = dados?.get(2)
            textView5.text = dados?.get(3)
            textView7.text = dados?.get(4)
        }
    }

    private fun carregarFragment(fragment: Fragment){
        activity?.let {
            it.supportFragmentManager
                .beginTransaction()
                .replace(R.id.menu_content, fragment)
                .addToBackStack("anterior")
                .commit()
        }
    }

    private fun atualizarFragmentNewInstance(lista: ArrayList<String>?, key: String, value: Char){

        var atualizarEmpresasFragment = AtualizarEmpresaFragment.newInstance()
        atualizarEmpresasFragment.arguments = Bundle().apply {
            this.putStringArrayList("dados", lista)
            this.putChar(key, value)
            carregarFragment(atualizarEmpresasFragment)
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