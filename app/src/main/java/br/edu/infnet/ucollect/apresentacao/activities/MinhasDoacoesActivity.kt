package br.edu.infnet.ucollect.apresentacao.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.adapters.ResiduoAdapter
import br.edu.infnet.ucollect.dominio.modelos.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_residuos.*

class MinhasDoacoesActivity : AppCompatActivity() {

    private lateinit var bancoDadosRef: DatabaseReference
    private lateinit var currentUser: FirebaseUser
    private lateinit var mAuth: FirebaseAuth
    private val usuarios = ArrayList<Usuario>()

    private var adapter: ResiduoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_residuos)

        mAuth = FirebaseAuth.getInstance()

        mAuth.currentUser?.let {
            currentUser = it
        }

        bancoDadosRef = FirebaseDatabase.getInstance().reference.child("usuarios-residuos").child(currentUser.uid)

    }

    override fun onStart() {
        super.onStart()

        FirebaseDatabase.getInstance().reference.child("usuarios").child(currentUser.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    // nada
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val usuario = p0.getValue(Usuario::class.java)
                    if(usuario == null){
                        return
                    }
                    usuarios.add(usuario)
                    adapter = ResiduoAdapter(bancoDadosRef,this@MinhasDoacoesActivity,usuarios,true)
                    setUpRecyclerView()
                }
            })
    }

    private fun setUpRecyclerView() {
        recycler_view_card_residuos.adapter = adapter
        recycler_view_card_residuos.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            if (recycler_view_card_residuos == null) {
                Toast.makeText(this, "Nenhum produto ofertado.", Toast.LENGTH_SHORT).show()
            }
    }
}
