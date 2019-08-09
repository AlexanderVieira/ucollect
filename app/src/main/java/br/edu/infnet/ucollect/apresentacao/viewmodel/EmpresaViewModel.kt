package br.edu.infnet.ucollect.apresentacao.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.ucollect.dominio.modelos.Empresa

class EmpresaViewModel: ViewModel() {
        
    val empresas = MutableLiveData<List<Empresa>>()
}