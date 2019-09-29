package br.edu.infnet.ucollect.apresentacao.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Residuo
import br.edu.infnet.ucollect.dominio.modelos.Usuario
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ResiduoAdapter(private val bancoDadosRef: DatabaseReference, private val contexto: android.content.Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val childEventListener: ChildEventListener
    private val residuos = ArrayList<Residuo>()
    private val usuarios = ArrayList<Usuario>()

    init {

        childEventListener = object: ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                residuos.add(p0.getValue(Residuo::class.java)!!)
                notifyItemInserted(residuos.size -1)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
        bancoDadosRef.addChildEventListener(childEventListener)

        FirebaseDatabase.getInstance().reference.child("usuarios").addChildEventListener(object: ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                usuarios.add(p0.getValue(Usuario::class.java)!!)
                notifyItemInserted(usuarios.size -1)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.residuo_card, parent,false)

        return ResiduoViewHolder(itemView)
    }

    override fun getItemCount() = residuos.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val residuo = residuos[position]

        if (holder is ResiduoViewHolder) {
            holder.nome.text = residuo.nome
            holder.descricao.text = residuo.descricao
            for(usuario in usuarios){
                if(residuo.doadorId == usuario.usuarioId){
                    holder.doador.text = usuario.apelido
                }
            }

            holder.imagem.setImageResource(R.drawable.ic_battery_20_black_24dp)
        }
    }


    class ResiduoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nome: TextView = itemView.findViewById(R.id.textView_card_doado_nome)
        val descricao: TextView = itemView.findViewById(R.id.textView_card_doado_descricao)
        val doador: TextView = itemView.findViewById(R.id.textView_card_residuo_doador)
        val imagem: ImageView = itemView.findViewById(R.id.imageView_card_doado)
    }
}



