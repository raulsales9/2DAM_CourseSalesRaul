package com.ieseljust.pmdm.whatsdam.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ieseljust.pmdm.whatsdam.R
import com.ieseljust.pmdm.whatsdam.repository.MessagesRepository

class MissatgesAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Constants per al tipus de missatge
    private val MISSATGE_D_USUARI = 1
    private val MISSATGE_D_ALTRE = 2

    // Referencia al repositori
    val repository=MessagesRepository.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /*
           Aquest metode s'invoca quan es crea el ViewHolder.
           Android ens proporciona viewType depres d'invocar getItemViewType
           - parent: el contenidor que conte la vista
           - ViewType: indica el tipus de vista
         */

        // Obtenim el layout inflatter
        val inflater = LayoutInflater.from(parent.context)

        // Segons el tipus de vista, utilitzarem un
        // o altre layout i ViewHolder.

        return when (viewType) {
            MISSATGE_D_USUARI -> {
                val vista = inflater.inflate(R.layout.my_msg_viewholder, parent, false)
                MissatgeViewHolder(vista)
            }
            MISSATGE_D_ALTRE -> { // MISSATGE_D_ALTRE
                val vista = inflater.inflate(R.layout.other_msg_viewholder, parent, false)
                MissatgeAltreViewHolder(vista)
            }
            else -> throw IllegalArgumentException("Vista desconeguda")

            // Cal observar que nomes s'ha generat la vista, pero
            // aci no s'enllacen dades
        }
    }

    override fun getItemCount(): Int {
        // Aquest metode retorna el nombre d'elements del DataSet
        return repository.getNumMessages()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Aquest metode  s'invoca quan s'enllacen les dades amb el ViewHolder

        // Caldra distingir segons la vista que siga, ja que
        // cada disseny te uns components diferents.
        if (getItemViewType(position) === MISSATGE_D_USUARI) {
            (holder as MissatgeViewHolder).bind(repository.getMessages()[position])
        } else {
            (holder as MissatgeAltreViewHolder).bind(repository.getMessages()[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        // Aquesta funcio permet definir el tipus de vista,
        // que es determina en funcio de la posició
        var message=repository.getMessages()[position]
        return if (message.username== repository.username ) {
            MISSATGE_D_USUARI
        } else {
            MISSATGE_D_ALTRE
        }
    }
}


