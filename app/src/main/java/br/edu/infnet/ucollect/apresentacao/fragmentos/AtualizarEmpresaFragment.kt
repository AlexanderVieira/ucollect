package br.edu.infnet.ucollect.apresentacao.fragmentos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Empresa
import br.edu.infnet.ucollect.infraestrutura.repositorios.EmpresaRepository
import kotlinx.android.synthetic.main.fragment_atualizar_empresa.*
import java.util.*

class AtualizarEmpresaFragment : Fragment() {

    private var dados : ArrayList<String>? = null
    private var isToUpdate : Char? = null
    private var isToDelete : Char? = null
    //private var isToSave : Char? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val arguments = arguments
        if (arguments != null) {
            //textViewQuestion.text = arguments.getString(STATEMENT_KEY)
            dados = arguments.getStringArrayList("dados")
            isToUpdate = arguments.getChar("atualizar")
            isToDelete = arguments.getChar("excluir")
            //isToSave = arguments.getChar("salvar")

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atualizar_empresa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dados?.let {
                edtxt_nome_atualizar_empresa_frg.setText(it.get(0))
                edtxt_email_atualizar_empresa_frg.setText(it.get(1))
                edtxt_tel_atualizar_empresa_frg.setText(it.get(2))
                edtxt_cnpj_atualizar_empresa_frg.setText(it.get(3))
                edtxt_end_atualizar_empresa_frg.setText(it.get(4))
            }

        if (isToUpdate == 'A'){
            btn_salvar_empresa_frg.visibility = View.GONE
            btn_excluir_empresa_frg.visibility = View.GONE
        }else if (isToDelete == 'D'){
            btn_salvar_empresa_frg.visibility = View.GONE
            btn_atualizar_empresa_frg.visibility = View.GONE
        }else{
            btn_atualizar_empresa_frg.visibility = View.GONE
            btn_excluir_empresa_frg.visibility = View.GONE
        }

        setListeners()
    }

    private fun setListeners(){
        btn_salvar_empresa_frg.setOnClickListener {

            var razaoSocial = edtxt_nome_atualizar_empresa_frg.text.toString()
            var email = edtxt_email_atualizar_empresa_frg.text.toString()
            var telefone = edtxt_tel_atualizar_empresa_frg.text.toString()
            var cnpj = edtxt_cnpj_atualizar_empresa_frg.text.toString()
            var endereco = edtxt_end_atualizar_empresa_frg.text.toString()
            //var imagem = imv_atualizar_empresa_frg.getTag() as Int

            var empresaRepository = EmpresaRepository()
            var empresas = empresaRepository.getEmpresas()
            var newEmpresa = Empresa(UUID.randomUUID().toString(),razaoSocial, cnpj,endereco,telefone,email,0)

            var index = empresas.indexOf(newEmpresa)
            if (index == -1){
                empresas.plus(Empresa(UUID.randomUUID().toString(),razaoSocial, cnpj,endereco,telefone,email,0))
                Toast.makeText(context, "Empresa cadastrada com sucesso!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, "Já existe empresa cadastrada!", Toast.LENGTH_LONG).show()
            }
        }

        btn_atualizar_empresa_frg.setOnClickListener {

            var razaoSocial = edtxt_nome_atualizar_empresa_frg.text.toString()
            var email = edtxt_email_atualizar_empresa_frg.text.toString()
            var telefone = edtxt_tel_atualizar_empresa_frg.text.toString()
            var cnpj = edtxt_cnpj_atualizar_empresa_frg.text.toString()
            var endereco = edtxt_end_atualizar_empresa_frg.text.toString()
            //var imagem = imv_atualizar_empresa_frg.getTag() as Int

            var empresaRepository = EmpresaRepository()
            var empresas = empresaRepository.getEmpresas()
            var empresaAtualizada = Empresa(UUID.randomUUID().toString(),razaoSocial, cnpj,endereco,telefone,email,0)
            var index = empresas.indexOf(empresaAtualizada)
            if (index > -1){

                empresas[index].empresaId = empresaAtualizada.empresaId
                empresas[index].razaoSocial = empresaAtualizada.razaoSocial
                empresas[index].email = empresaAtualizada.email
                empresas[index].telefone = empresaAtualizada.telefone
                empresas[index].cnpj = empresaAtualizada.cnpj
                empresas[index].imagemEmpresa = empresaAtualizada.imagemEmpresa
            }
            else{
                Toast.makeText(context, "Ocorreu um erro ao tentar Atualizar!", Toast.LENGTH_LONG).show()
            }
        }

        btn_excluir_empresa_frg.setOnClickListener {

            var razaoSocial = edtxt_nome_atualizar_empresa_frg.text.toString()
            var email = edtxt_email_atualizar_empresa_frg.text.toString()
            var telefone = edtxt_tel_atualizar_empresa_frg.text.toString()
            var cnpj = edtxt_cnpj_atualizar_empresa_frg.text.toString()
            var endereco = edtxt_end_atualizar_empresa_frg.text.toString()
            //var imagem = imv_atualizar_empresa_frg.getTag() as Int

            var empresaRepository = EmpresaRepository()
            var empresas = empresaRepository.getEmpresas()
            var empresaRecuperada = Empresa("1",razaoSocial, cnpj,endereco,telefone,email,0)

            var index = empresas.indexOf(empresaRecuperada)
            if (index > -1){

                val empresasExistentes = empresas.toMutableList().apply {
                    removeAt(index)
                }

            }else{
                Toast.makeText(context, "Ocorreu um erro ao tentar Excluir!", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        fun newInstance(): AtualizarEmpresaFragment{
            val atualizarEmpresaFragment = AtualizarEmpresaFragment()
            /*residuosFragment.arguments = Bundle().apply {
                // salva dados do parâmetro
            }*/
            return atualizarEmpresaFragment
        }
    }

}
