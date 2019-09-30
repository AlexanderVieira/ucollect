package br.edu.infnet.ucollect.infraestrutura.repositorios

import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Empresa
import java.util.*

class EmpresaRepository {

    //private lateinit var empresas: MutableList<Empresa>

    private val empresas = listOf<Empresa>(
        Empresa(UUID.randomUUID().toString(), "Associação Renovar", "86610819000196", "Rua A, 120", "21997258712", "teste1@teste.com.br", R.mipmap.ic_launcher_round),
        Empresa(UUID.randomUUID().toString(), "Coop Quitungo", "76433526000127", "Rua B, 121", "21997258713", "teste2@teste.com.br", R.mipmap.ic_launcher_round),
        Empresa(UUID.randomUUID().toString(), "Barra Coop Irajá", "72856420000185", "Rua C, 122", "21997258714", "teste3@teste.com.br", R.mipmap.ic_launcher_round),
        Empresa(UUID.randomUUID().toString(), "CoopFuturo", "55634761000197", "Rua D, 123", "21997258715", "teste4@teste.com.br", R.mipmap.ic_launcher_round),
        Empresa(UUID.randomUUID().toString(), "PEV Leroy Merlin", "51325818000115", "Rua E, 124", "21997258716", "teste5@teste.com.br", R.mipmap.ic_launcher_round)
    )

    fun getEmpresas() = empresas

    /*fun getEmpresas() : List<Empresa>{

        var bancoDados = FirebaseDatabase.getInstance().getReference()
        var bancoDadosRef = bancoDados.child("Empresas")

        empresas = mutableListOf()

        bancoDadosRef.addChildEventListener(object: ChildEventListener {
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                empresas.plus(p0.getValue(Empresa::class.java))
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return empresas
    }*/
}