package br.edu.infnet.ucollect.apresentacao.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Residuo

class ResiduoAdapter (var residuos: List<Residuo> = mutableListOf()): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.residuo_card, parent,false)

        return ResiduoViewHolder(itemView)
    }

    override fun getItemCount() = residuos.size

    fun setData(newResiduos: List<Residuo>){
        residuos = newResiduos
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val residuo = residuos[position]

        if(holder is ResiduoViewHolder){
            holder.nome.text = residuo.Nome
            holder.descricao.text = residuo.Descricao
            holder.doador.text = residuo.DoadorId.toString()
            holder.imagem.setImageResource(residuo.ImagemResiduo)
        }
    }


    class ResiduoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nome: TextView = itemView.findViewById(R.id.textView_card_doado_nome)
        val descricao: TextView = itemView.findViewById(R.id.textView_card_doado_descricao)
        val doador: TextView = itemView.findViewById(R.id.textView_card_residuo_doador)
        val imagem: ImageView = itemView.findViewById(R.id.imageView_card_doado)
    }
}



