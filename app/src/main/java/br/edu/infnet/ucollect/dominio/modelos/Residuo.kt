package br.edu.infnet.ucollect.dominio.modelos

class Residuo (val ResiduoId: String,
               val Nome: String,
               val Descricao: String,
               val DoadorId: String,
               val ImagemResiduo: Int){


    constructor(): this("","","","",0)

}