package com.ieseljust.pmdm.whatsdam

import android.util.Log
import java.io.Serializable

// Definim la data class Contacte
data class Message(
    val username: String,
    //Convertim el text a var per a que puga mutar pel edit text
    var text: String,
): Serializable

object Messages {
    private var _messages: ArrayList<Message>


    init {
        _messages = arrayListOf<Message>(
            Message("Casc Fosc", "Bé Rei Roland, està disposat a donar-nos la contrassenya per accedir al planeta Druidia?"),
            Message("Rei Roland", "Pel bé de la meua filla, us la diré"),
            Message("Rei Roland", "La contrassenya és... 1, 2, 3..."),
            Message("Casc Fosc", "1, 2, 3..."),
            Message("Rei Roland", "4 i 5"),
            Message("Casc Fosc", "4 i 5... Es la contrassenya que qualsevol idiota posaria a les seues maletes!"),
            Message("Casc Fosc", "President Scroob, ja tenim la clau d'accés!"),
            Message("Scroob", "Perfecte. Quina és?"),
            Message("Casc Fosc", "1 2 3 4 5"),
            Message("Scroob", "Fascinant! Es la mateixa combinació que tinc a les meues maletes!")

        )
    }

    // Nou missatge amb usuari i text
    fun add(username: String, text:String){
        _messages.add(Message(username=username, text=text))
    }

    // Nou missatge amb la classe missatge
    fun add(msg: Message) = _messages.add(msg)

    // Llista de missatges
    fun getMessages() = _messages

    // Retorna el numero de missatges
    fun getNumMessages() = _messages.size


    fun deleteMessage(msg: Message): Int {
        val index = _messages.indexOfFirst { it.username == msg.username && it.text == msg.text }
        if (index != -1) {
            _messages[index].text = "El mensaje ha sido eliminado"
        } else {
            Log.d("Debug", "Missatge no trobat: $msg")
        }
        return index
    }


}