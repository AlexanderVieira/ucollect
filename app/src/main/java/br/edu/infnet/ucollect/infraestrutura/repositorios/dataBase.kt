package br.edu.infnet.ucollect.infraestrutura.repositorios

import br.edu.infnet.ucollect.dominio.modelos.Empresa

var empresas = listOf<Empresa>(
    Empresa("Empresa Teste1",
        1111111111,
        "Rua a",
        "99965-6782",
        "teste@teste.com"),
    Empresa("Empresa Teste2",
        1111111111,
        "Rua b",
        "99965-6789",
        "teste1@teste.com"
    ))