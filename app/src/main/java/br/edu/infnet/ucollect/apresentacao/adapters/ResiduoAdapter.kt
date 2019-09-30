package br.edu.infnet.ucollect.apresentacao.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.activities.ResiduoDetalhesActivity
import br.edu.infnet.ucollect.dominio.modelos.Residuo
import br.edu.infnet.ucollect.dominio.modelos.Usuario
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ResiduoAdapter(private val bancoDadosRef: DatabaseReference, private val contexto: android.content.Context, private val usuarios: ArrayList<Usuario>, var meusResiduos: Boolean = false): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val childEventListener: ChildEventListener
    private val residuos = ArrayList<Residuo>()
    private val residuosId = ArrayList<String>()

    private lateinit var residuo: Residuo

    init {

        childEventListener = object: ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                val novoResiduo = p0.getValue(Residuo::class.java)
                val chave = p0.key
                val residuoIndex = residuosId.indexOf(chave)
                if(residuoIndex > -1 && novoResiduo != null){
                    residuos[residuoIndex] = novoResiduo
                    notifyItemChanged(residuoIndex)
                }
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                residuos.add(p0.getValue(Residuo::class.java)!!)
                residuosId.add(p0.key!!)
                notifyItemInserted(residuos.size -1)
            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

        }
        bancoDadosRef.addChildEventListener(childEventListener)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.residuo_card, parent,false)

        itemView.setOnClickListener {
            val intent = Intent(contexto,ResiduoDetalhesActivity::class.java)
            intent.putExtra("residuo",residuos.first {temp ->
                temp.residuoId == it.findViewById<TextView>(R.id.textView_card_doado_residuoId).text.toString()
            })

            intent.putExtra("apelidoDoador",it.findViewById<TextView>(R.id.textView_card_residuo_doador).text.toString())

            intent.putExtra("meusResiduos", meusResiduos)

            contexto.startActivity(intent)
        }

        return ResiduoViewHolder(itemView)
    }

    override fun getItemCount() = residuos.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        residuo = residuos[position]
            if (holder is ResiduoViewHolder) {
                holder.nome.text = residuo.nome
                holder.descricao.text = residuo.descricao
                for(usuario in usuarios){
                    if(residuo.doadorId == usuario.usuarioId){
                        holder.doador.text = usuario.apelido
                    }
                }
                holder.residuoId.text = residuo.residuoId
                holder.imagem.setImageResource(R.drawable.ic_battery_20_black_24dp)
            }

    }


    class ResiduoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var residuoId: TextView = itemView.findViewById(R.id.textView_card_doado_residuoId)
        val nome: TextView = itemView.findViewById(R.id.textView_card_doado_nome)
        val descricao: TextView = itemView.findViewById(R.id.textView_card_doado_descricao)
        val doador: TextView = itemView.findViewById(R.id.textView_card_residuo_doador)
        val imagem: ImageView = itemView.findViewById(R.id.imageView_card_doado)
    }
}



