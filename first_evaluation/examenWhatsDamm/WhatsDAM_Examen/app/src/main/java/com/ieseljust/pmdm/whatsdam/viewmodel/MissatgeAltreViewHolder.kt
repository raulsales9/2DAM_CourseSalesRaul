package com.ieseljust.pmdm.whatsdam.viewmodel

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ieseljust.pmdm.whatsdam.Message
import com.ieseljust.pmdm.whatsdam.R
import java.text.SimpleDateFormat
import java.util.Date

class MissatgeAltreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Necessitem la vista per a l'hora i el text del missatge

    val data = itemView.findViewById(R.id.msg_other_timestamp) as TextView
    val text = itemView.findViewById(R.id.msg_other_text) as TextView
    val user = itemView.findViewById(R.id.msg_text_usuari) as TextView
    //afegim el control sobre el boto de respondre
    val respondre = itemView.findViewById(R.id.button_reply) as FloatingActionButton

    // Funcio encarregada de suministrar a la vista dades que anem a treballar
    // Enllacem les dades del missatge amb la vista
    fun bind(missatge: Message) {
        text.setText(missatge.text)
        user.setText(missatge.username)
        // Per a la data, posem l'hora actual
        // Per a aixo utilitzem SimpleDataFormat i Date
        val dateFormat = SimpleDateFormat("HH:mm")
        val horaActual = Date()
        data.setText(dateFormat.format(horaActual))
    }


}
