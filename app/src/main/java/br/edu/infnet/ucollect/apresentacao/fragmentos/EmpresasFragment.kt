package br.edu.infnet.ucollect.apresentacao.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.adapters.EmpresaAdapter
import br.edu.infnet.ucollect.apresentacao.viewmodel.EmpresaViewModel
import kotlinx.android.synthetic.main.fragment_empresas.*

class EmpresasFragment : Fragment() {

    private lateinit var empresaViewModel: EmpresaViewModel

    val empresaAdapter: EmpresaAdapter by lazy {
        EmpresaAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView =  inflater.inflate(R.layout.fragment_empresas, container, false)

        // Inicia o ViewModel
        activity?.let {
            empresaViewModel = ViewModelProviders
                .of(it)
                .get(EmpresaViewModel::class.java)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        subscribe()
    }

    // Define a RecycleView
    private fun setUpRecyclerView(){

        recycler_view_id.adapter = empresaAdapter

        activity?.let {
            recycler_view_id.layoutManager = LinearLayoutManager(it)
        }

    }

    // Observa o estado do viewModel
    private fun subscribe(){
        empresaViewModel.getEmpresas().observe(this, Observer {empresas->

            val empresaAdapter = recycler_view_id.adapter
            if (empresaAdapter is EmpresaAdapter){
                if(empresas.isEmpty()){
                    Toast.makeText(context, "Nenhuma Empresa cadastrada.", Toast.LENGTH_SHORT).show()
                }else{
                    empresaAdapter.setData(empresas)
                }
            }
        })
    }

    companion object {
        fun newInstance(): EmpresasFragment{
            val empresasFragment = EmpresasFragment()
            /*residuosFragment.arguments = Bundle().apply {
                // salva dados do parâmetro
            }*/
            return empresasFragment
        }
    }
}
