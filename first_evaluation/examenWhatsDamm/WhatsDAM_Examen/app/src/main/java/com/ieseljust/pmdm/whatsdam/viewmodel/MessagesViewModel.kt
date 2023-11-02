package com.ieseljust.pmdm.whatsdam.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ieseljust.pmdm.whatsdam.Message
import com.ieseljust.pmdm.whatsdam.repository.MessagesRepository

class MessagesViewModel(application: Application):
    AndroidViewModel(application) {

    /* Propietats del ViewModel */

    private val _adaptador = MutableLiveData<MissatgesAdapter>().apply{
        value= MissatgesAdapter(
            {missatge: Message, v: View -> ReplyManager(missatge, v)},
            {missatge: Message, v: View -> RemoveMsgManager(missatge, v)}
        )
    }
    val adaptador:MutableLiveData<MissatgesAdapter> =_adaptador
    private val _resposta = MutableLiveData<String>()
    val resposta = MutableLiveData<String>()
    val _latestInserted= MutableLiveData<Int>().apply{
        value= 0
    }
    val latestInserted:MutableLiveData<Int> =_latestInserted
    val replyMessage = MutableLiveData<String>() // LiveData para la respuesta
        //MutableLiveData<String>().apply{ value = "" }
    // Referencia al Repositori
    var repository=MessagesRepository.getInstance()

    public fun addMessage(msg: Message){
        // Afegim un missatge a traves de la instancia
        // del repositori
        repository.add(msg)
        _latestInserted.value=repository.getNumMessages()-1;
        _adaptador.value?.notifyItemInserted(latestInserted.value?:0)

        // I aci no fem el scroll. Aixo ho fara la vista.
    }

    //Funcio oer a respondre un missatge
    public fun respondre(msg: Message, v: View){
        Log.d("Debug", msg.text)
        _resposta.value ="usuari" + msg.username + "missatge" + msg.text
    }

    // Callback per a borrar
    public fun supostBorrat(msg: Message, v: View) {
        val posicio = repository.deleteMessage(msg)
        _adaptador.value?.notifyDataSetChanged(posicio)
        return false
    }



}
