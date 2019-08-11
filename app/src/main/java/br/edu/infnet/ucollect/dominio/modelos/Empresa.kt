package br.edu.infnet.ucollect.dominio.modelos

class Empresa(val empresaId: Int,
              val nome: String,
              val cnpj: Long,
              val endereco: String,
              val telefone: String,
              val email: String)