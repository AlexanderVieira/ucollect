package br.edu.infnet.ucollect.apresentacao.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.ucollect.dominio.modelos.Residuo
import br.edu.infnet.ucollect.infraestrutura.repositorios.ResiduoRepository

class ResiduoViewModel: ViewModel(){

    private val _residuoRepository = ResiduoRepository()
    private var _residuos: MutableLiveData<List<Residuo>>? = null

    fun getResiduos(): LiveData<List<Residuo>>{
        if(_residuos == null){
            _residuos = MutableLiveData()
            val tmpResiduos = _residuoRepository.getResiduos()
            _residuos!!.postValue(tmpResiduos)
        }

        return _residuos!!
    }
}