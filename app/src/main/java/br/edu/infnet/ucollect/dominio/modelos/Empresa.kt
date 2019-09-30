package br.edu.infnet.ucollect.dominio.modelos

import com.google.firebase.database.Exclude

class Empresa(val empresaId: String,
              val razaoSocial: String,
              val cnpj: String,
              val endereco: String,
              val telefone: String,
              val email: String,
              val imagemEmpresa: Int)
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