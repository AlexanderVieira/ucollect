package br.edu.infnet.ucollect.apresentacao.fragmentos


import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.adapters.ResiduoAdapter
import br.edu.infnet.ucollect.apresentacao.viewmodel.ResiduoViewModel
import kotlinx.android.synthetic.main.fragment_empresas.*
import kotlinx.android.synthetic.main.fragment_residuos.*
import kotlinx.android.synthetic.main.residuo_card.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ResiduosFragment : Fragment() {

    private lateinit var residuoViewModel: ResiduoViewModel

    val residuoAdapter: ResiduoAdapter by lazy {
        ResiduoAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.let{
            residuoViewModel = ViewModelProviders
                .of(it)
                .get(ResiduoViewModel::class.java)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_residuos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        subscribe()
    }


    private fun setUpRecyclerView() {

        recycler_view_card_residuos.adapter = residuoAdapter

        activity?.let{
            recycler_view_card_residuos.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }
    }


    private fun subscribe() {
        residuoViewModel.getResiduos().observe(this, Observer {residuos ->
            val residuoAdapter = recycler_view_card_residuos.adapter
            if(residuoAdapter is ResiduoAdapter){
                if(residuos.isEmpty()){
                    Toast.makeText(context, "Nenhum produto ofertado.", Toast.LENGTH_SHORT).show()
                }
                else{
                    residuoAdapter.setData(residuos)
                }
            }

        })
    }


}
