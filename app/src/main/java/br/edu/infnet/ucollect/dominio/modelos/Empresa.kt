package br.edu.infnet.ucollect.dominio.modelos

import com.google.firebase.database.Exclude

class Empresa(var empresaId: String,
              var razaoSocial: String,
              var cnpj: String,
              var endereco: String,
              var telefone: String,
              var email: String,
              var imagemEmpresa: Int)
{
    constructor(): this("","","","", "", "",0)

    @Exclude
    fun toMap(): Map<String, Any?>{
        return mapOf(
            "empresaId" to empresaId,
            "razaoSocial" to razaoSocial,
            "cnpj" to cnpj,
            "endereco" to endereco,
            "telefone" to telefone,
            "email" to email,
            "imagemEmpresa" to imagemEmpresa
        )
    }
}