package br.edu.infnet.ucollect.infraestrutura.repositorios

import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.dominio.modelos.Residuo

class ResiduoRepository {

    private val residuos = listOf<Residuo>(
        Residuo(1, "Bateria", "XPTO1", "1", R.drawable.ic_battery_20_black_24dp),
        Residuo(2, "Computador","XPTO2", "1", R.drawable.ic_computer_black_24dp),
        Residuo(3, "Teclado","XPTO3", "2", R.drawable.ic_keyboard_black_24dp),
        Residuo(4, "Mémoria","XPTO4", "5", R.drawable.ic_memory_black_24dp),
        Residuo(5, "Smartphone","XPTO5", "8", R.drawable.ic_smartphone_black_24dp)
    )

    fun getResiduos() = residuos
}