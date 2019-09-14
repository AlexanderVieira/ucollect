package br.edu.infnet.ucollect.dominio.modelos

class Usuario(val usuarioId:Int,
              val nome:String,
              val apelido:String,
              val email:String,
              val senha:String,
              val confirmaSenha:String) {
}