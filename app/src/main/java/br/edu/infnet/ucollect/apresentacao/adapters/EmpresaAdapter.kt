package br.edu.infnet.ucollect.apresentacao.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Empresa

class EmpresaAdapter(var empresas: List<Empresa> = mutableListOf()): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.empresa_card, parent, false)
        return EmpresaViewHolder(itemView)
    }

    override fun getItemCount() = empresas.size

    fun setData(newEmpresas: List<Empresa>){
        empresas = newEmpresas
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val empresa = empresas[position]
        if (holder is EmpresaViewHolder){
            holder.nome.text = empresa.nome
            holder.cnpj.text = empresa.cnpj.toString()
            holder.endereco.text = empresa.endereco
            holder.telefone.text = empresa.telefone
            holder.email.text = empresa.email
        }
    }

    class EmpresaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nome: TextView = itemView.findViewById(R.id.textView_card_nome)
        val cnpj: TextView = itemView.findViewById(R.id.textView_card_cnpj)
        val endereco: TextView = itemView.findViewById(R.id.textView_card_endereco)
        val telefone: TextView = itemView.findViewById(R.id.textView_card_telefone)
        val email: TextView = itemView.findViewById(R.id.textView_card_email)
    }
}