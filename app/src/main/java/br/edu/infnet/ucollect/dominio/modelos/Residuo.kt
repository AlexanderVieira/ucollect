package br.edu.infnet.ucollect.dominio.modelos

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Residuo (val residuoId: String,
               val nome: String,
               val descricao: String,
               val doadorId: String,
               val imagemResiduo: Int){


    constructor(): this("","","","",0)

    @Exclude
    fun toMap(): Map<String, Any?>{
        return mapOf(
            "residuoId" to residuoId,
            "nome" to nome,
            "descricao" to descricao,
            "doadorId" to doadorId,
            "imagemResiduo" to imagemResiduo
        )
    }
}