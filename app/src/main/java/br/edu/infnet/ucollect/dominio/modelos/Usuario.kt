package br.edu.infnet.ucollect.dominio.modelos

class Usuario(var usuarioId:String,
              var nome:String,
              var apelido:String,
              var email:String,
              var senha:String,
              var confirmaSenha:String) {

    constructor():this("","","","","","")

    override fun toString(): String {
        return "$usuarioId\n$nome\n$apelido\n$email\n$senha\n$confirmaSenha"
    }
}