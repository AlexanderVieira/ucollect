package br.edu.infnet.ucollect.apresentacao.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.adapters.ResiduoAdapter
import br.edu.infnet.ucollect.dominio.modelos.Usuario
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_residuos.*


class ResiduosFragment : Fragment() {

    private lateinit var bancoDadosRef: DatabaseReference

    private var adapter: ResiduoAdapter? = null

    private lateinit var usuarios: ArrayList<Usuario>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bancoDadosRef = FirebaseDatabase.getInstance().reference
            .child("residuos")

        usuarios = ArrayList()

        FirebaseDatabase.getInstance().reference.child("usuarios").addChildEventListener(object: ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                usuarios.add(p0.getValue(Usuario::class.java)!!)
            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_residuos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            adapter = ResiduoAdapter(bancoDadosRef,it, usuarios)
            setUpRecyclerView()
        }
        //subscribe()
    }

    private fun setUpRecyclerView() {
        activity?.let{
            recycler_view_card_residuos.adapter = adapter
            recycler_view_card_residuos.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            if(recycler_view_card_residuos == null)
            {
                Toast.makeText(context, "Nenhum produto ofertado.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*private fun subscribe() {
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
    }*/

    companion object {
        fun newInstance(): ResiduosFragment{
            val residuosFragment = ResiduosFragment()
            /*residuosFragment.arguments = Bundle().apply {
                // salva dados do parâmetro
            }*/
            return residuosFragment
        }
    }
}
