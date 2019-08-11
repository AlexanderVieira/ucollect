package br.edu.infnet.ucollect.apresentacao.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.ucollect.dominio.modelos.Empresa
import br.edu.infnet.ucollect.infraestrutura.repositorios.EmpresaRepository
import br.edu.infnet.ucollect.infraestrutura.repositorios.getEmpresas

class EmpresaViewModel: ViewModel() {

    private val _empresaRepository = EmpresaRepository()
    private var _empresas: MutableLiveData<List<Empresa>>? = null

    fun getEmpresas(): LiveData<List<Empresa>> {
        if (_empresas == null){
            _empresas = MutableLiveData()
            val tmpEmpresas = _empresaRepository.getEmpresas()
            _empresas!!.postValue(tmpEmpresas)
        }
        return _empresas!!
    }

    //val empresas = MutableLiveData<List<Empresa>>().apply { value = getEmpresas }

}