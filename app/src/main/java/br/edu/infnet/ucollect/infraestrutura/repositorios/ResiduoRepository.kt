package br.edu.infnet.ucollect.infraestrutura.repositorios

import android.util.Log
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Residuo
import com.google.firebase.database.*

class ResiduoRepository {


    private lateinit var residuos: List<Residuo>


    /*  listOf<Residuo>(
        Residuo("1", "Bateria", "XPTO1", "1", R.drawable.ic_battery_20_black_24dp),
        Residuo("2", "Computador","XPTO2", "1", R.drawable.ic_computer_black_24dp),
        Residuo("3", "Teclado","XPTO3", "2", R.drawable.ic_keyboard_black_24dp),
        Residuo("4", "Mémoria","XPTO4", "5", R.drawable.ic_memory_black_24dp),
        Residuo("5", "Smartphone","XPTO5", "8", R.drawable.ic_smartphone_black_24dp)
    )*/

    fun getResiduos() : List<Residuo>{

        var bancoDados = FirebaseDatabase.getInstance().getReference()

        var bancoDadosRef = bancoDados.child("Residuos")

        residuos = mutableListOf()

        bancoDadosRef.addChildEventListener(object: ChildEventListener {
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                residuos.plus(p0.getValue(Residuo::class.java))
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return residuos
    }

}